    private static final ByteList intToUnsignedByteList(int i, int shift, byte[] digitmap) {
        byte[] buf = new byte[32];
        int charPos = 32;
        int radix = 1 << shift;
        long mask = radix - 1;
        do {
            buf[--charPos] = digitmap[(int)(i & mask)];
            i >>>= shift;
        } while (i != 0);
        return new ByteList(buf, charPos, (32 - charPos), false);
    }
