    @Override public void toXContent(XContentBuilder builder, Params params) throws IOException {
        if (keyScript == null) {
            throw new SearchSourceBuilderException("key_script must be set on histogram script facet for facet [" + name + "]");
        }
        if (valueScript == null) {
            throw new SearchSourceBuilderException("value_script must be set on histogram script facet for facet [" + name + "]");
        }
        builder.startObject(name);

        builder.startObject(HistogramFacetCollectorParser.NAME);
        builder.field("key_script", keyScript);
        builder.field("value_script", valueScript);
        if (interval > 0) { // interval is optional in script facet, can be defined by the key script
            builder.field("interval", interval);
        }
        if (this.params != null) {
            builder.field("params");
            builder.map(this.params);
        }
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
