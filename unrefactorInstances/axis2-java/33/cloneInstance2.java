    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < tokens.length; i++) {
            NMToken token = tokens[i];
            if (i > 0) buf.append(" ");
            buf.append(token.toString());
        }
        return buf.toString();
    }
