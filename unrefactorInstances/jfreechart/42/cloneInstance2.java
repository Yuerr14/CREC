    public void testNullBlock_FN() {
        BlockContainer c = new BlockContainer(new GridArrangement(1, 1));
        c.add(null);
        Size2D s = c.arrange(null, RectangleConstraint.NONE.toFixedWidth(10));
        assertEquals(10.0, s.getWidth(), EPSILON);
        assertEquals(0.0, s.getHeight(), EPSILON);
    }
