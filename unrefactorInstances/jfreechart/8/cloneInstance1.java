    public void testGetObject() {
        // retrieve an item
        KeyedObjects ko1 = new KeyedObjects();
        ko1.addObject("Key 1", "Object 1");
        ko1.addObject("Key 2", null);
        ko1.addObject("Key 3", "Object 2");
        assertEquals("Object 1", ko1.getObject(0));
        assertNull(ko1.getObject(1));
        assertEquals("Object 2", ko1.getObject(2));

        // request with a negative index
        boolean pass = false;
        try {
            ko1.getObject(-1);
        }
        catch (IndexOutOfBoundsException e) {
            pass = true;
        }
        assertTrue(pass);

        // request width index == itemCount
        pass = false;
        try {
            ko1.getObject(3);
        }
        catch (IndexOutOfBoundsException e) {
            pass = true;
        }
        assertTrue(pass);
    }
