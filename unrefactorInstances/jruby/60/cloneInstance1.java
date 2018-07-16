        if (rslen == 0) {
            while (len > 0 && bytes[p + len - 1] == (byte)'\n') {
                len--;
                if (len > 0 && bytes[p + len - 1] == (byte)'\r') len--;
            }
            if (len < value.getRealSize()) {
                view(0, len);
                return this;
            }
            return runtime.getNil();
        }
