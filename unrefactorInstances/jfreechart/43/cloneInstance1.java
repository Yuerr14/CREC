    public void testCalculateColumnTotal() {
        DefaultKeyedValues2D table = new DefaultKeyedValues2D();
        table.addValue(new Double(1.0), "R0", "C0");
        table.addValue(new Double(2.0), "R0", "C1");
        table.addValue(new Double(3.0), "R1", "C0");
        table.addValue(new Double(4.0), "R1", "C1");
        assertEquals(4.0, DataUtilities.calculateColumnTotal(table, 0), EPSILON);
        assertEquals(6.0, DataUtilities.calculateColumnTotal(table, 1), EPSILON);
        table.setValue(null, "R1", "C1");
        assertEquals(2.0, DataUtilities.calculateColumnTotal(table, 1), EPSILON);
    }
