    public void testSerialization() {
        TickLabelEntity e1 = new TickLabelEntity(
            new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0), "ToolTip", "URL"
        ); 
        TickLabelEntity e2 = null;
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(e1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray())
            );
            e2 = (TickLabelEntity) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(e1, e2);
    }
