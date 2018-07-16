    public void testIndexOf() {
        XIntervalSeries s1 = new XIntervalSeries("Series 1");
        s1.add(1.0, 1.0, 1.0, 2.0);
        s1.add(2.0, 2.0, 2.0, 3.0);
        s1.add(3.0, 3.0, 3.0, 4.0);
        assertEquals(0, s1.indexOf(new Double(1.0)));
    }
