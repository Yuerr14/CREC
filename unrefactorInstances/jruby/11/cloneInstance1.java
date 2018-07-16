    public static FileResource fileResource(ThreadContext context, IRubyObject pathOrFile) {
        Ruby runtime = context.runtime;

        if (pathOrFile instanceof RubyFile) {
            return JRubyFile.createResource(runtime, ((RubyFile) pathOrFile).getPath());
        } else if (pathOrFile instanceof RubyIO) {
            return JRubyFile.createResource(runtime, ((RubyIO) pathOrFile).openFile.getPath());

        }

        RubyString path = StringSupport.checkEmbeddedNulls(runtime, get_path(context, pathOrFile));
        return JRubyFile.createResource(runtime, path.toString());
    }
