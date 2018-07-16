    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < idrefs.length; i++) {
            IDRef ref = idrefs[i];
            if (i > 0) buf.append(" ");
            buf.append(ref.toString());
        }
        return buf.toString();
    }
