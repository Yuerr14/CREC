    public void testCloning() throws CloneNotSupportedException {
        XYItemEntity e1 = new XYItemEntity(new Rectangle2D.Double(1.0, 2.0,
                3.0, 4.0), new TimeSeriesCollection(), 1, 9, "ToolTip", "URL");
        XYItemEntity e2 = (XYItemEntity) e1.clone();
        assertTrue(e1 != e2);
        assertTrue(e1.getClass() == e2.getClass());
        assertTrue(e1.equals(e2));
    }
