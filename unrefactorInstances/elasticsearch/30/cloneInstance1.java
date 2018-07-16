    public void testGlobalMaxRetriesBackcompat() {
        Settings settings = Settings.builder()
            .put(S3Repository.Repositories.MAX_RETRIES_SETTING.getKey(), 10)
            .build();
        launchAWSConfigurationTest(settings, Settings.EMPTY, Protocol.HTTPS, null, -1, null,
            null, 10, false, 50000);
        assertSettingDeprecationsAndWarnings(new Setting<?>[]{
            S3Repository.Repositories.MAX_RETRIES_SETTING
        });
    }
