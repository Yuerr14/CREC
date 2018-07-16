    public IRubyObject op_equal(IRubyObject other) {
        if (!(other instanceof JavaProxyReflectionObject)) {
            Object otherObj = other.dataGetStruct();
            if (!(otherObj instanceof JavaObject)) {
                return getRuntime().getFalse();
            }
            other = (IRubyObject)otherObj;
        }

        boolean isEqual = equals(other);
        return isEqual ? getRuntime().getTrue() : getRuntime().getFalse();
    }
