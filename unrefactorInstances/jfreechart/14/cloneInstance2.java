    public void testSerialization() throws IOException, ClassNotFoundException {
        Week w1 = new Week(24, 1999);
        Week w2;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(buffer);
        out.writeObject(w1);
        out.close();
        ObjectInput in = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray()));
        w2 = (Week) in.readObject();
        in.close();
        assertEquals(w1, w2);
    }
