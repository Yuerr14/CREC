    public void testEmptyAggregation() throws Exception {

        SearchResponse searchResponse = client().prepareSearch("empty_bucket_idx")
                .setQuery(matchAllQuery())
                .addAggregation(histogram("histo").field("value").interval(1l).minDocCount(0).subAggregation(avg("avg")))
                .execute().actionGet();

        assertThat(searchResponse.getHits().getTotalHits(), equalTo(2l));
        Histogram histo = searchResponse.getAggregations().get("histo");
        assertThat(histo, notNullValue());
        Histogram.Bucket bucket = histo.getBuckets().get(1);
        assertThat(bucket, notNullValue());

        Avg avg = bucket.getAggregations().get("avg");
        assertThat(avg, notNullValue());
        assertThat(avg.getName(), equalTo("avg"));
        assertThat(Double.isNaN(avg.getValue()), is(true));
    }
