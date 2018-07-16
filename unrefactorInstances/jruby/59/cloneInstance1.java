    private static final int getNativeLibraryFlags(IRubyObject rbFlags) {
        int f = 0, flags = RubyNumeric.fix2int(rbFlags);
        f |= (flags & RTLD_LAZY) != 0 ? Library.LAZY : 0;
        f |= (flags & RTLD_NOW) != 0 ? Library.NOW : 0;
        f |= (flags & RTLD_LOCAL) != 0 ? Library.LOCAL : 0;
        f |= (flags & RTLD_GLOBAL) != 0 ? Library.GLOBAL : 0;
        return f;
    }
