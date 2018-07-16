    public void testRemoveValueInt() {
        KeyedObjects ko1 = new KeyedObjects();
        ko1.setObject("Key 1", "Object 1");
        ko1.setObject("Key 2", null);
        ko1.setObject("Key 3", "Object 2");

        ko1.removeValue(1);
        assertEquals(2, ko1.getItemCount());
        assertEquals(1, ko1.getIndex("Key 3"));


        // try negative key index
        boolean pass = false;
        try {
            ko1.removeValue(-1);
        }
        catch (IndexOutOfBoundsException e) {
            pass = true;
        }
        assertTrue(pass);

        // try key index == itemCount
        pass = false;
        try {
            ko1.removeValue(2);
        }
        catch (IndexOutOfBoundsException e) {
            pass = true;
        }
        assertTrue(pass);
    }
