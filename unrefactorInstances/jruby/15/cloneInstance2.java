    public IRubyObject same(IRubyObject other) {
        if (!(other instanceof JavaObject)) {
            Object otherObj = other.dataGetStruct();
            if (!(otherObj instanceof JavaObject)) {
                return getRuntime().getFalse();
            }
            other = (IRubyObject)otherObj;
        }

        boolean isSame = this == other;
        return isSame ? getRuntime().getTrue() : getRuntime().getFalse();
    }
