    public void testRemoveAgedItems4() {
        TimeSeries s1 = new TimeSeries("S1");
        s1.setMaximumItemAge(2);
        s1.add(new Year(2010), 1.1);
        s1.add(new Year(2011), 2.2);
        s1.add(new Year(2012), 3.3);
        s1.add(new Year(2013), 2.5);
        assertEquals(3, s1.getItemCount());
        assertEquals(2.2, s1.getMinY(), EPSILON);
        assertEquals(3.3, s1.getMaxY(), EPSILON);
    }
