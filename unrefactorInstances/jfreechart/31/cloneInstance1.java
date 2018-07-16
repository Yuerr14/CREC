    public void testEquals_ObjectList() {
        BarRenderer r1 = new BarRenderer();
        r1.setSeriesItemLabelFont(0, new Font(Font.DIALOG, Font.BOLD, 10));
        BarRenderer r2 = new BarRenderer();
        r2.setSeriesItemLabelFont(0, new Font(Font.DIALOG, Font.BOLD, 10));
        assertEquals(r1, r2);
        r2.setSeriesItemLabelFont(1, new Font(Font.DIALOG, Font.PLAIN, 5));
        assertNotEquals(r1, r2);
    }
