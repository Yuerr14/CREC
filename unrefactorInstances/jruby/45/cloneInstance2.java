    private static final ByteList longToUnsignedByteList(long i, int shift, byte[] digitmap) {
        byte[] buf = new byte[64];
        int charPos = 64;
        int radix = 1 << shift;
        long mask = radix - 1;
        do {
            buf[--charPos] = digitmap[(int)(i & mask)];
            i >>>= shift;
        } while (i != 0);
        return new ByteList(buf, charPos, (64 - charPos), false);
    }
