    public void withShardSize_string_withExecutionHintMap_singleShard() throws Exception {

        client().admin().indices().prepareCreate("idx")
                .addMapping("type", "key", "type=string,index=not_analyzed")
                .execute().actionGet();

        indexData();

        SearchResponse response = client().prepareSearch("idx").setTypes("type").setRouting("1")
                .setQuery(matchAllQuery())
                .addFacet(termsFacet("keys").field("key").size(3).shardSize(5).executionHint("map").order(TermsFacet.ComparatorType.COUNT))
                .execute().actionGet();

        Facets facets = response.getFacets();
        TermsFacet terms = facets.facet("keys");
        List<? extends TermsFacet.Entry> entries = terms.getEntries();
        assertThat(entries.size(), equalTo(3)); // we still only return 3 entries (based on the 'size' param)
        Map<String, Integer> expected = ImmutableMap.<String, Integer>builder()
                .put("1", 5)
                .put("2", 4)
                .put("3", 3) // <-- count is now fixed
                .build();
        for (TermsFacet.Entry entry : entries) {
            assertThat(entry.getCount(), equalTo(expected.get(entry.getTerm().string())));
        }
    }
