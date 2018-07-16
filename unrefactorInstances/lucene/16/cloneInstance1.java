  public void testModifyResolution() {
    try {
      // increase and test
      long resolution = 20 * TimeLimitingCollector.DEFAULT_RESOLUTION; //400
      TimeLimitingCollector.setResolution(resolution);
      assertEquals(resolution, TimeLimitingCollector.getResolution());
      doTestTimeout(false,true);
      // decrease much and test
      resolution = 5;
      TimeLimitingCollector.setResolution(resolution);
      assertEquals(resolution, TimeLimitingCollector.getResolution());
      doTestTimeout(false,true);
      // return to default and test
      resolution = TimeLimitingCollector.DEFAULT_RESOLUTION;
      TimeLimitingCollector.setResolution(resolution);
      assertEquals(resolution, TimeLimitingCollector.getResolution());
      doTestTimeout(false,true);
    } finally {
      TimeLimitingCollector.setResolution(TimeLimitingCollector.DEFAULT_RESOLUTION);
    }
  }
