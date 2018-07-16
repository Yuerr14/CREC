    public void testRemove() {
        
        XYSeries s1 = new XYSeries("Series 1");
        s1.add(1.0, 1.0);
        s1.add(2.0, 2.0);
        s1.add(3.0, 3.0);
        
        assertEquals(3, s1.getItemCount());
        s1.remove(new Double(2.0));
        assertEquals(new Double(3.0), s1.getX(1));
        
        s1.remove(0);
        assertEquals(new Double(3.0), s1.getX(0));
        
    }
