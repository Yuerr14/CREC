  private void testMetricStream() throws Exception {

    indexr(id, "0", "a_s", "hello0", "a_i", "100", "a_f", "0");
    indexr(id, "2", "a_s", "hello0", "a_i", "2", "a_f", "0");
    indexr(id, "3", "a_s", "hello3", "a_i", "3", "a_f", "3");
    indexr(id, "4", "a_s", "hello3", "a_i", "4", "a_f", "4");
    indexr(id, "1", "a_s", "hello1", "a_i", "1", "a_f", "1");
    indexr(id, "6", "a_s", "hello1", "a_i", "1", "a_f", "1");
    indexr(id, "7", "a_s", "hello1", "a_i", "1", "a_f", "1");

    commit();

    String zkHost = zkServer.getZkAddress();

    Bucket[] buckets = {new Bucket("a_s")};
    Metric[] metrics = {new SumMetric("a_i", false),
                        new MeanMetric("a_i", false),
                        new CountMetric(),
                        new MinMetric("a_i", false),
                        new MaxMetric("a_i", false)};

    Map params = mapParams("q","*:*","fl","id,a_s,a_i","sort", "a_i asc");
    CloudSolrStream stream = new CloudSolrStream(zkHost, "collection1", params);
    MetricStream mstream = new MetricStream(stream, buckets, metrics, "metric1", new DescBucketComp(0),5);
    getTuples(mstream);

    BucketMetrics[] bucketMetrics = mstream.getBucketMetrics();
    assert(bucketMetrics.length == 3);

    //Bucket should be is descending order based on Metric 0, which is the SumMetric.

    assert(bucketMetrics[0].getKey().toString().equals("hello0"));
    assert(bucketMetrics[1].getKey().toString().equals("hello3"));
    assert(bucketMetrics[2].getKey().toString().equals("hello1"));

    assertMetric(bucketMetrics[0].getMetrics()[0], 102.0d); //Test the first Metric of the first BucketMetrics
    assertMetric(bucketMetrics[0].getMetrics()[1], 51.0d); //Test the second Metric of the first BucketMetrics
    assertMetric(bucketMetrics[0].getMetrics()[2], 2.0d); //Test the third Metric of the first BucketMetrics
    assertMetric(bucketMetrics[0].getMetrics()[3], 2.0d); //Test the fourth Metric of the first BucketMetrics
    assertMetric(bucketMetrics[0].getMetrics()[4], 100.0d); //Test the fifth Metric of the first BucketMetrics


    assertMetric(bucketMetrics[1].getMetrics()[0], 7.0d);
    assertMetric(bucketMetrics[2].getMetrics()[0], 3.0d);


    params = mapParams("q","*:*","fl","id,a_s,a_i","sort", "a_i asc");
    stream = new CloudSolrStream(zkHost, "collection1", params);
    mstream = new MetricStream(stream, buckets, metrics, "metric1", new AscBucketComp(0),5);
    getTuples(mstream);

    bucketMetrics = mstream.getBucketMetrics();

    assertMetric(bucketMetrics[0].getMetrics()[0], 3.0d); //Test the first Metric of the first BucketMetrics
    assertMetric(bucketMetrics[0].getMetrics()[1], 1.0d); //Test the second Metric of the first BucketMetrics
    assertMetric(bucketMetrics[0].getMetrics()[2], 3.0d); //Test the third Metric of the first BucketMetrics
    assertMetric(bucketMetrics[0].getMetrics()[3], 1.0d); //Test the fourth Metric of the first BucketMetrics
    assertMetric(bucketMetrics[0].getMetrics()[4], 1.0d); //Test the fifth Metric of the first BucketMetrics

    assertMetric(bucketMetrics[1].getMetrics()[0], 7.0d);
    assertMetric(bucketMetrics[2].getMetrics()[0], 102.0d);

    indexr(id, "8", "a_s", "hello4", "a_i", "1000", "a_f", "1"); //Add a fourth record.
    commit();

    //Test desc comp with more buckets then priority queue can hold.
    params = mapParams("q","*:*","fl","id,a_s,a_i","sort", "a_i asc");
    stream = new CloudSolrStream(zkHost, "collection1", params);
    mstream = new MetricStream(stream, buckets, metrics, "metric1", new DescBucketComp(0),3);
    getTuples(mstream);

    bucketMetrics = mstream.getBucketMetrics();
    assert(bucketMetrics.length == 3);
    assert(bucketMetrics[0].getKey().toString().equals("hello4"));
    assert(bucketMetrics[1].getKey().toString().equals("hello0"));
    assert(bucketMetrics[2].getKey().toString().equals("hello3"));

    //Test asc comp with more buckets then priority queue can hold.
    params = mapParams("q","*:*","fl","id,a_s,a_i","sort", "a_i asc");
    stream = new CloudSolrStream(zkHost, "collection1", params);
    mstream = new MetricStream(stream, buckets, metrics, "metric1", new AscBucketComp(0),3);
    getTuples(mstream);

    bucketMetrics = mstream.getBucketMetrics();
    assert(bucketMetrics.length == 3);
    assert(bucketMetrics[0].getKey().toString().equals("hello1"));
    assert(bucketMetrics[1].getKey().toString().equals("hello3"));
    assert(bucketMetrics[2].getKey().toString().equals("hello0"));


    //Test with no buckets
    params = mapParams("q","*:*","fl","id,a_s,a_i","sort", "a_i asc");
    stream = new CloudSolrStream(zkHost, "collection1", params);
    mstream = new MetricStream(stream, metrics, "metric1");
    getTuples(mstream);

    bucketMetrics = mstream.getBucketMetrics();
    assert(bucketMetrics.length == 1);
    assert(bucketMetrics[0].getKey().toString().equals("metrics"));
    assertMetric(bucketMetrics[0].getMetrics()[0], 1112.0d); //Test the first Metric of the first BucketMetrics

    del("*:*");
    commit();
  }
