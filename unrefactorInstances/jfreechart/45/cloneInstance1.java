    public void testFindLiveItemsLowerBound_Unordered() {
        DefaultXYDataset d = new DefaultXYDataset();

        // check a series with no items
        d.addSeries("S1", new double[][] {{}, {}});
        assertEquals(0, RendererUtils.findLiveItemsLowerBound(d, 0, 10.0,
                11.0));

        // check a series with one item
        d.addSeries("S2", new double[][] {{0.0}, {9.9}});
        assertEquals(0, RendererUtils.findLiveItemsLowerBound(d, 1, 0.0,
                1.1));
        assertEquals(0, RendererUtils.findLiveItemsLowerBound(d, 1, 2.0,
                3.3));

        // check a series with two items
        d.addSeries("S3", new double[][] {{0.0, 1.0}, {9.9, 9.9}});
        assertEquals(0, RendererUtils.findLiveItemsLowerBound(d, 2, 0.0,
                1.1));
        assertEquals(1, RendererUtils.findLiveItemsLowerBound(d, 2, 1.0,
                2.2));
        assertEquals(1, RendererUtils.findLiveItemsLowerBound(d, 2, 2.0,
                3.3));
        assertEquals(1, RendererUtils.findLiveItemsLowerBound(d, 2, 3.0,
                4.4));

        // check a series with three items
        d.addSeries("S4", new double[][] {{1.0, 2.0, 1.5}, {9.9, 9.9, 9.9}});
        assertEquals(0, RendererUtils.findLiveItemsLowerBound(d, 3, 0.0,
                1.1));
        assertEquals(0, RendererUtils.findLiveItemsLowerBound(d, 3, 1.0,
                2.2));
        assertEquals(1, RendererUtils.findLiveItemsLowerBound(d, 3, 2.0,
                3.3));
        assertEquals(2, RendererUtils.findLiveItemsLowerBound(d, 3, 3.0,
                4.4));

        // check a series with four items
        d.addSeries("S5", new double[][] {{1.0, 2.0, 1.5, 1.8}, {9.9, 9.9,
                9.9, 9.9}});
        assertEquals(0, RendererUtils.findLiveItemsLowerBound(d, 4, 0.0,
                1.1));
        assertEquals(0, RendererUtils.findLiveItemsLowerBound(d, 4, 1.0,
                2.2));
        assertEquals(1, RendererUtils.findLiveItemsLowerBound(d, 4, 2.0,
                3.3));
        assertEquals(3, RendererUtils.findLiveItemsLowerBound(d, 4, 3.0,
                4.4));
        assertEquals(3, RendererUtils.findLiveItemsLowerBound(d, 4, 4.0,
                5.5));
    }
