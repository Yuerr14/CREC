    protected SignificantTermsAggregationBuilder createTestAggregatorBuilder() {
        String name = randomAlphaOfLengthBetween(3, 20);
        SignificantTermsAggregationBuilder factory = new SignificantTermsAggregationBuilder(name, null);
        String field = randomAlphaOfLengthBetween(3, 20);
        randomFieldOrScript(factory, field);

        if (randomBoolean()) {
            factory.missing("MISSING");
        }
        if (randomBoolean()) {
            factory.bucketCountThresholds().setRequiredSize(randomIntBetween(1, Integer.MAX_VALUE));

        }
        if (randomBoolean()) {
            factory.bucketCountThresholds().setShardSize(randomIntBetween(1, Integer.MAX_VALUE));
        }
        if (randomBoolean()) {
            int minDocCount = randomInt(4);
            switch (minDocCount) {
            case 0:
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                minDocCount = randomIntBetween(0, Integer.MAX_VALUE);
                break;
            }
            factory.bucketCountThresholds().setMinDocCount(minDocCount);
        }
        if (randomBoolean()) {
            int shardMinDocCount = randomInt(4);
            switch (shardMinDocCount) {
            case 0:
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                shardMinDocCount = randomIntBetween(0, Integer.MAX_VALUE);
                break;
            default:
                fail();
            }
            factory.bucketCountThresholds().setShardMinDocCount(shardMinDocCount);
        }
        if (randomBoolean()) {
            factory.executionHint(randomFrom(executionHints));
        }
        if (randomBoolean()) {
            factory.format("###.##");
        }
        if (randomBoolean()) {
            IncludeExclude incExc = getIncludeExclude();
            factory.includeExclude(incExc);
        }
        if (randomBoolean()) {
            SignificanceHeuristic significanceHeuristic = getSignificanceHeuristic();
            factory.significanceHeuristic(significanceHeuristic);
        }
        if (randomBoolean()) {
            factory.backgroundFilter(QueryBuilders.termsQuery("foo", "bar"));
        }
        return factory;
    }
