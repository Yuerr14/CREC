    public void testLeftShift() {
        // byte
        assertEquals((byte) 60, exec("byte x = 15; x <<= 2; return x;"));
        assertEquals((byte) -60, exec("byte x = (byte) -15; x <<= 2; return x;"));
        // short
        assertEquals((short) 60, exec("short x = 15; x <<= 2; return x;"));
        assertEquals((short) -60, exec("short x = (short) -15; x <<= 2; return x;"));
        // char
        assertEquals((char) 60, exec("char x = (char) 15; x <<= 2; return x;"));
        // int
        assertEquals(60, exec("int x = 15; x <<= 2; return x;"));
        assertEquals(-60, exec("int x = -15; x <<= 2; return x;"));
        // long
        assertEquals(60L, exec("long x = 15L; x <<= 2; return x;"));
        assertEquals(-60L, exec("long x = -15L; x <<= 2; return x;"));
    }
