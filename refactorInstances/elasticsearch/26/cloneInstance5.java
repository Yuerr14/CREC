    public void withShardSize_long() throws Exception {

        client().admin().indices().prepareCreate("idx")
                .addMapping("type", "key", "type=long")
                .execute().actionGet();

        indexData();

        SearchResponse response = client().prepareSearch("idx").setTypes("type")
                .setQuery(matchAllQuery())
                .addAggregation(terms("keys").field("key").size(3).shardSize(5).order(Terms.Order.count(false)))
                .execute().actionGet();

        Terms terms = response.getAggregations().get("keys");
        Collection<Terms.Bucket> buckets = terms.getBuckets();
        assertThat(buckets.size(), equalTo(3)); // we still only return 3 entries (based on the 'size' param)
        Map<Integer, Long> expected = ImmutableMap.<Integer, Long>builder()
                .put(1, 8l)
                .put(3, 8l)
                .put(2, 5l) // <-- count is now fixed
                .build();
        for (Terms.Bucket bucket : buckets) {
            assertThat(bucket.getDocCount(), equalTo(expected.get(bucket.getKeyAsNumber().intValue())));
        }
    }
