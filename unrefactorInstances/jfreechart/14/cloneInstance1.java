    public void testSerialization() throws IOException, ClassNotFoundException {
        CategoryLabelWidthType w1 = CategoryLabelWidthType.RANGE;
        CategoryLabelWidthType w2;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(buffer);
        out.writeObject(w1);
        out.close();

        ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(
                buffer.toByteArray()));
        w2 = (CategoryLabelWidthType) in.readObject();
        in.close();
        assertEquals(w1, w2);
        assertTrue(w1 == w2);
    }
