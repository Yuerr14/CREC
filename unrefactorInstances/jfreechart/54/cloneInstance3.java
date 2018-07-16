    public void testSerialization() {
        CategoryLabelEntity e1 = new CategoryLabelEntity("A", 
                new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL"); 
        CategoryLabelEntity e2 = null;
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(e1);
            out.close();

            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(
                    buffer.toByteArray()));
            e2 = (CategoryLabelEntity) in.readObject();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(e1, e2);
    }
