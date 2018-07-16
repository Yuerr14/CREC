    public void testGetKey() {
        // retrieve an item
        KeyedObjects ko1 = new KeyedObjects();
        ko1.addObject("Key 1", "Object 1");
        ko1.addObject("Key 2", null);
        ko1.addObject("Key 3", "Object 2");
        assertEquals("Key 1", ko1.getKey(0));
        assertEquals("Key 2", ko1.getKey(1));
        assertEquals("Key 3", ko1.getKey(2));

        // request with a negative index
        boolean pass = false;
        try {
            ko1.getKey(-1);
        }
        catch (IndexOutOfBoundsException e) {
            pass = true;
        }
        assertTrue(pass);

        // request width index == itemCount
        pass = false;
        try {
            ko1.getKey(3);
        }
        catch (IndexOutOfBoundsException e) {
            pass = true;
        }
        assertTrue(pass);
    }
