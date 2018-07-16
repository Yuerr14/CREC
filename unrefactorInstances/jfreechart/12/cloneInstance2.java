    public void testDelete() {
        XYSeries s1 = new XYSeries("S1");
        s1.add(1.0, 1.1);
        s1.add(2.0, 2.2);
        s1.add(3.0, 3.3);
        s1.add(4.0, 4.4);
        s1.add(5.0, 5.5);
        s1.add(6.0, 6.6);
        s1.delete(2, 5);
        assertEquals(2, s1.getItemCount());
        assertEquals(1.0, s1.getX(0).doubleValue(), EPSILON);
        assertEquals(2.0, s1.getX(1).doubleValue(), EPSILON);
        assertEquals(1.0, s1.getMinX(), EPSILON);
        assertEquals(2.0, s1.getMaxX(), EPSILON);
        assertEquals(1.1, s1.getMinY(), EPSILON);
        assertEquals(2.2, s1.getMaxY(), EPSILON);
    }
