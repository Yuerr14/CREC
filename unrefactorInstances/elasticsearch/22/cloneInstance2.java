    @Test public void testPrefixNamedFilteredQuery() throws IOException {
        IndexQueryParserService queryParser = queryParser();
        String query = copyToStringFromClasspath("/org/elasticsearch/index/query/prefix-filter-named.json");
        ParsedQuery parsedQuery = queryParser.parse(query);
        assertThat(parsedQuery.namedFilters().containsKey("test"), equalTo(true));
        assertThat(parsedQuery.query(), instanceOf(FilteredQuery.class));
        FilteredQuery filteredQuery = (FilteredQuery) parsedQuery.query();
        PrefixFilter prefixFilter = (PrefixFilter) filteredQuery.getFilter();
        assertThat(prefixFilter.getPrefix(), equalTo(new Term("name.first", "sh")));
    }
