    public void testSerialization() throws IOException, ClassNotFoundException {
        CategoryLabelPositions p1 = CategoryLabelPositions.STANDARD;
        CategoryLabelPositions p2;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(buffer);
        out.writeObject(p1);
        out.close();

        ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(
                buffer.toByteArray()));
        p2 = (CategoryLabelPositions) in.readObject();
        in.close();
        assertEquals(p1, p2);
    }
