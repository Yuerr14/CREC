    public void test2502355_restoreAutoDomainBounds() {
        DefaultXYDataset dataset = new DefaultXYDataset();
        JFreeChart chart = ChartFactory.createXYLineChart("TestChart", "X",
                "Y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainAxis(1, new NumberAxis("X2"));
        ChartPanel panel = new ChartPanel(chart);
        chart.addChangeListener(this);
        this.chartChangeEvents.clear();
        panel.restoreAutoDomainBounds();
        assertEquals(1, this.chartChangeEvents.size());
    }
