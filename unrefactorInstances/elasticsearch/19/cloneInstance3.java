    public void testUnsignedRightShift() {
        // byte
        assertEquals((byte) 15, exec("byte x = 60; x >>>= 2; return x;"));
        assertEquals((byte) -15, exec("byte x = (byte) -60; x >>>= 2; return x;"));
        // short
        assertEquals((short) 15, exec("short x = 60; x >>>= 2; return x;"));
        assertEquals((short) -15, exec("short x = (short) -60; x >>>= 2; return x;"));
        // char
        assertEquals((char) 15, exec("char x = (char) 60; x >>>= 2; return x;"));
        // int
        assertEquals(15, exec("int x = 60; x >>>= 2; return x;"));
        assertEquals(-60 >>> 2, exec("int x = -60; x >>>= 2; return x;"));
        // long
        assertEquals(15L, exec("long x = 60L; x >>>= 2; return x;"));
        assertEquals(-60L >>> 2, exec("long x = -60L; x >>>= 2; return x;"));
    }
