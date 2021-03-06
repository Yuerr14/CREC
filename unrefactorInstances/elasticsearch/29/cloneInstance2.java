    public void testParseMatchSimpleStringValue() throws Exception {
        parser = YamlXContent.yamlXContent.createParser(
                "{ foo: bar }"
        );

        MatchParser matchParser = new MatchParser();
        MatchAssertion matchAssertion = matchParser.parse(new ClientYamlTestSuiteParseContext("api", "suite", parser));

        assertThat(matchAssertion, notNullValue());
        assertThat(matchAssertion.getField(), equalTo("foo"));
        assertThat(matchAssertion.getExpectedValue(), instanceOf(String.class));
        assertThat(matchAssertion.getExpectedValue().toString(), equalTo("bar"));
    }
