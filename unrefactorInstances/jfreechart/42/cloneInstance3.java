    public void testNullBlock_NN() {
        BlockContainer c = new BlockContainer(new GridArrangement(1, 1));
        c.add(null);
        Size2D s = c.arrange(null, RectangleConstraint.NONE);
        assertEquals(0.0, s.getWidth(), EPSILON);
        assertEquals(0.0, s.getHeight(), EPSILON);
    }
