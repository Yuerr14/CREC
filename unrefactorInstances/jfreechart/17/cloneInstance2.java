    public void testSerialization() {
        double[] starts_S1 = new double[] {0.1, 0.2, 0.3};
        double[] starts_S2 = new double[] {0.3, 0.4, 0.5};
        double[] ends_S1 = new double[] {0.5, 0.6, 0.7};
        double[] ends_S2 = new double[] {0.7, 0.8, 0.9};
        double[][] starts = new double[][] {starts_S1, starts_S2};
        double[][] ends = new double[][] {ends_S1, ends_S2};
        DefaultIntervalCategoryDataset d1
                = new DefaultIntervalCategoryDataset(starts, ends);
        DefaultIntervalCategoryDataset d2 = (DefaultIntervalCategoryDataset) 
                TestUtilities.serialised(d1);
        assertEquals(d1, d2);
    }
