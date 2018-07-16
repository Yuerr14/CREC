    public void testSerialization() {
        XYItemEntity e1 = new XYItemEntity(
            new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0),
            new TimeSeriesCollection(), 1, 9, "ToolTip", "URL"
        ); 
        XYItemEntity e2 = null;
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(e1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray())
            );
            e2 = (XYItemEntity) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(e1, e2);
    }
