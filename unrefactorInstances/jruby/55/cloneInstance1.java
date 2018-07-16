    public int loadCertificateFile(String file, int type) throws Exception {
        if (file == null) {
            return 1;
        }
        int count = 0;
        int ret = 0;
        Reader reader = null;
        try {
            InputStream in = wrapJRubyNormalizedInputStream(file);
            X509AuxCertificate x = null;
            if (type == X509Utils.X509_FILETYPE_PEM) {
                reader = new BufferedReader(new InputStreamReader(in));
                for (;;) {
                    x = PEMInputOutput.readX509Aux(reader, null);
                    if (null == x) {
                        break;
                    }
                    int i = store.addCertificate(x);
                    if (i == 0) {
                        return ret;
                    }
                    count++;
                    x = null;
                }
                ret = count;
            } else if (type == X509Utils.X509_FILETYPE_ASN1) {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                x = StoreContext.ensureAux((X509Certificate) cf.generateCertificate(in));
                if (x == null) {
                    X509Error.addError(13);
                    return ret;
                }
                int i = store.addCertificate(x);
                if (i == 0) {
                    return ret;
                }
                ret = i;
            } else {
                X509Error.addError(X509Utils.X509_R_BAD_X509_FILETYPE);
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ignored) {
                }
            }
        }
        return ret;
    }
