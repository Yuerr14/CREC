    public void testGetFilterHandleNumericParseErrorStrict() throws Exception {
        NumericRangeFilterBuilder filterBuilder = new NumericRangeFilterBuilder();
        filterBuilder.setStrictMode(true);

        String xml = "<NumericRangeFilter fieldName='AGE' type='int' lowerTerm='-1' upperTerm='NaN'/>";
        Document doc = getDocumentFromString(xml);
        try {
            filterBuilder.getFilter(doc.getDocumentElement());
        } catch (ParserException e) {
            return;
        }
        fail("Expected to throw " + ParserException.class);
    }
