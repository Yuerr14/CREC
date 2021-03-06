    @Override public void toXContent(XContentBuilder builder, Params params) throws IOException {
        if (keyFieldName == null) {
            throw new SearchSourceBuilderException("field must be set on histogram facet for facet [" + name + "]");
        }
        if (interval < 0) {
            throw new SearchSourceBuilderException("interval must be set on histogram facet for facet [" + name + "]");
        }
        builder.startObject(name);

        builder.startObject(HistogramFacetCollectorParser.NAME);
        if (valueFieldName != null && !keyFieldName.equals(valueFieldName)) {
            builder.field("key_field", keyFieldName);
            builder.field("value_field", valueFieldName);
        } else {
            builder.field("field", keyFieldName);
        }
        builder.field("interval", interval);
        if (comparatorType != null) {
            builder.field("comparator", comparatorType.description());
        }
        builder.endObject();

        if (filter != null) {
            builder.field("filter");
            filter.toXContent(builder, params);
        }

        if (global != null) {
            builder.field("global", global);
        }

        builder.endObject();
    }
