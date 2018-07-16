    public void testGetFilterHandleNumericParseErrorStrict() throws Exception {
        NumericRangeQueryBuilder filterBuilder = new NumericRangeQueryBuilder();

        String xml = "<NumericRangeQuery fieldName='AGE' type='int' lowerTerm='-1' upperTerm='NaN'/>";
        Document doc = getDocumentFromString(xml);
        try {
            filterBuilder.getQuery(doc.getDocumentElement());
        } catch (ParserException e) {
            return;
        }
        fail("Expected to throw " + ParserException.class);
    }
