    private static int getNativeLibraryFlags(IRubyObject rbFlags) {
        int f = 0, flags = RubyNumeric.fix2int(rbFlags);
        f |= (flags & LAZY) != 0 ? Library.LAZY : 0;
        f |= (flags & NOW) != 0 ? Library.NOW : 0;
        f |= (flags & LOCAL) != 0 ? Library.LOCAL : 0;
        f |= (flags & GLOBAL) != 0 ? Library.GLOBAL : 0;
        return f;
    }
