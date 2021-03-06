    public SpanWithinQueryBuilder fromXContent(QueryParseContext parseContext) throws IOException, QueryParsingException {
        XContentParser parser = parseContext.parser();

        float boost = AbstractQueryBuilder.DEFAULT_BOOST;
        String queryName = null;
        SpanQueryBuilder big = null;
        SpanQueryBuilder little = null;

        String currentFieldName = null;
        XContentParser.Token token;
        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
            if (token == XContentParser.Token.FIELD_NAME) {
                currentFieldName = parser.currentName();
            } else if (token == XContentParser.Token.START_OBJECT) {
                if ("big".equals(currentFieldName)) {
                    QueryBuilder query = parseContext.parseInnerQueryBuilder();
                    if (query instanceof SpanQueryBuilder == false) {
                        throw new QueryParsingException(parseContext, "span_within [big] must be of type span query");
                    }
                    big = (SpanQueryBuilder) query;
                } else if ("little".equals(currentFieldName)) {
                    QueryBuilder query = parseContext.parseInnerQueryBuilder();
                    if (query instanceof SpanQueryBuilder == false) {
                        throw new QueryParsingException(parseContext, "span_within [little] must be of type span query");
                    }
                    little = (SpanQueryBuilder) query;
                } else {
                    throw new QueryParsingException(parseContext, "[span_within] query does not support [" + currentFieldName + "]");
                }
            } else if ("boost".equals(currentFieldName)) {
                boost = parser.floatValue();
            } else if ("_name".equals(currentFieldName)) {
                queryName = parser.text();
            } else {
                throw new QueryParsingException(parseContext, "[span_within] query does not support [" + currentFieldName + "]");
            }
        }

        if (big == null) {
            throw new QueryParsingException(parseContext, "span_within must include [big]");
        }
        if (little == null) {
            throw new QueryParsingException(parseContext, "span_within must include [little]");
        }

        SpanWithinQueryBuilder query = new SpanWithinQueryBuilder(big, little);
        query.boost(boost).queryName(queryName);
        return query;
    }
