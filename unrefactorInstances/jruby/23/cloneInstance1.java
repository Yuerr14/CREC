    private static int unescapeUnicodeList(RubyContext context, ByteList to, byte[]bytes, int p, int end, Encoding[]encp, ByteList str, RegexpSupport.ErrorMode mode) {
        while (p < end && ASCIIEncoding.INSTANCE.isSpace(bytes[p] & 0xff)) p++;

        boolean hasUnicode = false;
        while (true) {
            int code = StringSupport.scanHex(bytes, p, end - p);
            int len = StringSupport.hexLength(bytes, p, end - p);
            if (len == 0) break;
            if (len > 6) raisePreprocessError(context, str, "invalid Unicode range", mode);
            p += len;
            if (to != null) appendUtf8(context, to, code, encp, str, mode);
            hasUnicode = true;
            while (p < end && ASCIIEncoding.INSTANCE.isSpace(bytes[p] & 0xff)) p++;
        }

        if (!hasUnicode) raisePreprocessError(context, str, "invalid Unicode list", mode);
        return p;
    }
