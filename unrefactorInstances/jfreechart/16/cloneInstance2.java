    public void testAddOrUpdate2() {
        TimeSeries s1 = new TimeSeries("S1");
        s1.setMaximumItemCount(2);
        s1.addOrUpdate(new Year(2010), 1.1);
        s1.addOrUpdate(new Year(2011), 2.2);
        s1.addOrUpdate(new Year(2012), 3.3);
        assertEquals(2, s1.getItemCount());
        assertEquals(2.2, s1.getMinY(), EPSILON);
        assertEquals(3.3, s1.getMaxY(), EPSILON);
    }
