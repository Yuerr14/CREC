    public void testUpdateRequestWithBothScriptAndDoc() throws Exception {
        createIndex();
        ClusterHealthResponse clusterHealth = client().admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID).setWaitForGreenStatus().execute().actionGet();
        assertThat(clusterHealth.isTimedOut(), equalTo(false));
        assertThat(clusterHealth.getStatus(), equalTo(ClusterHealthStatus.GREEN));

        try {
            client().prepareUpdate("test", "type1", "1")
                    .setDoc(XContentFactory.jsonBuilder().startObject().field("field", 1).endObject())
                    .setScript("ctx._source.field += 1")
                    .execute().actionGet();
            fail("Should have thrown ActionRequestValidationException");
        } catch (ActionRequestValidationException e) {
            assertThat(e.validationErrors().size(), equalTo(1));
            assertThat(e.validationErrors().get(0), containsString("can't provide both script and doc"));
            assertThat(e.getMessage(), containsString("can't provide both script and doc"));
        }
    }
