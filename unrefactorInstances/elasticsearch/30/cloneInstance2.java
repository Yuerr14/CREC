    public void testGlobalThrottleRetriesBackcompat() {
        Settings settings = Settings.builder()
            .put(S3Repository.Repositories.USE_THROTTLE_RETRIES_SETTING.getKey(), true)
            .build();
        launchAWSConfigurationTest(settings, Settings.EMPTY, Protocol.HTTPS, null, -1, null,
            null, 3, true, 50000);
        assertSettingDeprecationsAndWarnings(new Setting<?>[]{
            S3Repository.Repositories.USE_THROTTLE_RETRIES_SETTING
        });
    }
