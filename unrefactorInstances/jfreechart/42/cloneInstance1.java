    public void testNullBlock_FF() {
        BlockContainer c = new BlockContainer(new GridArrangement(1, 1));
        c.add(null);
        Size2D s = c.arrange(null, new RectangleConstraint(20, 10));
        assertEquals(20.0, s.getWidth(), EPSILON);
        assertEquals(10.0, s.getHeight(), EPSILON);
    }
