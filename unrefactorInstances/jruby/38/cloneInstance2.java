    public void buildNewString(ArrayCallback callback, int count, Encoding encoding) {
        loadRuntime();
        method.ldc(StandardASMCompiler.STARTING_DSTR_FACTOR * count);
        if (encoding == null) {
            method.invokestatic(p(RubyString.class), "newStringLight", sig(RubyString.class, Ruby.class, int.class));
        } else {
            script.getCacheCompiler().cacheEncoding(this, encoding);
            method.invokestatic(p(RubyString.class), "newStringLight", sig(RubyString.class, Ruby.class, int.class, Encoding.class));
        }

        for (int i = 0; i < count; i++) {
            callback.nextValue(this, null, i);
        }
    }
