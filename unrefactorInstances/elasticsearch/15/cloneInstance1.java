    public void testTypesGetter() {
        int numTypes = between(1, 50);
        String[] types = new String[numTypes];
        for (int i = 0; i < numTypes; i++) {
            types[i] = randomSimpleString(random(), 1, 30);
        }
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.types(types);
        DeleteByQueryRequest request = new DeleteByQueryRequest(searchRequest);
        assertArrayEquals(request.types(), types);
    }
