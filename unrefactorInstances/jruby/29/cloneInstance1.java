    public ByteList[] getByteNames() {
        int nameLength = pattern.numberOfNames();
        if (nameLength == 0) return EMPTY_BYTELIST_ARRAY;

        ByteList[] names = new ByteList[nameLength];
        int j = 0;
        for (Iterator<NameEntry> i = pattern.namedBackrefIterator(); i.hasNext();) {
            NameEntry e = i.next();
            names[j++] = new ByteList(e.name, e.nameP, e.nameEnd - e.nameP, pattern.getEncoding(), true);
        }

        return names;
    }
