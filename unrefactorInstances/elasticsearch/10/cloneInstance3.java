    public static NestedAggregationBuilder parse(String aggregationName, QueryParseContext context) throws IOException {
        String path = null;

        XContentParser.Token token;
        String currentFieldName = null;
        XContentParser parser = context.parser();
        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
            if (token == XContentParser.Token.FIELD_NAME) {
                currentFieldName = parser.currentName();
            } else if (token == XContentParser.Token.VALUE_STRING) {
                if (context.getParseFieldMatcher().match(currentFieldName, NestedAggregator.PATH_FIELD)) {
                    path = parser.text();
                } else {
                    throw new ParsingException(parser.getTokenLocation(),
                            "Unknown key for a " + token + " in [" + aggregationName + "]: [" + currentFieldName + "].");
                }
            } else {
                throw new ParsingException(parser.getTokenLocation(), "Unexpected token " + token + " in [" + aggregationName + "].");
            }
        }

        if (path == null) {
            // "field" doesn't exist, so we fall back to the context of the ancestors
            throw new ParsingException(parser.getTokenLocation(), "Missing [path] field for nested aggregation [" + aggregationName + "]");
        }

        return new NestedAggregationBuilder(aggregationName, path);
    }
