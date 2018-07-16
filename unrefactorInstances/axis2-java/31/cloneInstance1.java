    public void addHeader(String name, String value) {

        if ((name == null) || "".equals(name)) {
            throw new IllegalArgumentException("Illegal MimeHeader name");
        }

        int i = headers.size();

        for (int j = i - 1; j >= 0; j--) {
            MimeHeader mimeheader = (MimeHeader) headers.elementAt(j);

            if (mimeheader.getName().equalsIgnoreCase(name)) {
                headers.insertElementAt(new MimeHeader(name, value), j + 1);

                return;
            }
        }

        headers.addElement(new MimeHeader(name, value));
    }
