            for (; i <= ITERS; i++) {
                BulkRequestBuilder request = client.prepareBulk();
                for (int j = 0; j < BATCH; j++) {
                    counter++;
                    XContentBuilder source = jsonBuilder().startObject()
                            .field("id", Integer.valueOf(counter))
                            .field("l_value", lValues[counter % lValues.length])
                            .field("date", new Date())
                            .endObject();
                    request.add(Requests.indexRequest("test").type("type1").id(Integer.toString(counter))
                            .source(source));
                }
                BulkResponse response = request.execute().actionGet();
                if (response.hasFailures()) {
                    System.err.println("--> failures...");
                }
                if (((i * BATCH) % 10000) == 0) {
                    System.out.println("--> Indexed " + (i * BATCH) + " took " + stopWatch.stop().lastTaskTime());
                    stopWatch.start();
                }
            }
