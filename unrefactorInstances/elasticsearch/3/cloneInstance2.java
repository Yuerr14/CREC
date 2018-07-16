            Map<String, Object> metaData) throws IOException {
        super(name, context, parent, subFactories, metaData);
        IndexSearcher contextSearcher = context.searcher();
        this.separator = separator;
        weights = new Weight[filters.size()];
        keys = new String[filters.size()];
        for (int i = 0; i < filters.size(); ++i) {
            KeyedFilter keyedFilter = filters.get(i);
            this.keys[i] = keyedFilter.key();
            Query filter = keyedFilter.filter().toFilter(context.getQueryShardContext());
            this.weights[i] = contextSearcher.createNormalizedWeight(filter, false);
        }
    }
