    private static int unescapeUnicodeList(Ruby runtime, ByteList to, byte[]bytes, int p, int end, Encoding[]encp, ByteList str, ErrorMode mode) {
        while (p < end && ASCIIEncoding.INSTANCE.isSpace(bytes[p] & 0xff)) p++;

        boolean hasUnicode = false;
        while (true) {
            int code = StringSupport.scanHex(bytes, p, end - p);
            int len = StringSupport.hexLength(bytes, p, end - p);
            if (len == 0) break;
            if (len > 6) raisePreprocessError(runtime, str, "invalid Unicode range", mode);
            p += len;
            if (to != null) appendUtf8(runtime, to, code, encp, str, mode);
            hasUnicode = true;
            while (p < end && ASCIIEncoding.INSTANCE.isSpace(bytes[p] & 0xff)) p++;
        }

        if (!hasUnicode) raisePreprocessError(runtime, str, "invalid Unicode list", mode);
        return p;
    }
