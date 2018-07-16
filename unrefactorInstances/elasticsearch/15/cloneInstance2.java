    public void testTypesSetter() {
        int numTypes = between(1, 50);
        String[] types = new String[numTypes];
        for (int i = 0; i < numTypes; i++) {
            types[i] = randomSimpleString(random(), 1, 30);
        }
        SearchRequest searchRequest = new SearchRequest();
        DeleteByQueryRequest request = new DeleteByQueryRequest(searchRequest);
        request.types(types);
        assertArrayEquals(request.types(), types);
    }
