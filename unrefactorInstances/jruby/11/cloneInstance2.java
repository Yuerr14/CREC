    public static FileResource fileResource(IRubyObject pathOrFile) {
        Ruby runtime = pathOrFile.getRuntime();

        if (pathOrFile instanceof RubyFile) {
            return JRubyFile.createResource(runtime, ((RubyFile) pathOrFile).getPath());
        } else if (pathOrFile instanceof RubyIO) {
            return JRubyFile.createResource(runtime, ((RubyIO) pathOrFile).openFile.getPath());
        }

        ThreadContext context = runtime.getCurrentContext();
        RubyString path = StringSupport.checkEmbeddedNulls(runtime, get_path(context, pathOrFile));
        return JRubyFile.createResource(runtime, path.toString());
    }
