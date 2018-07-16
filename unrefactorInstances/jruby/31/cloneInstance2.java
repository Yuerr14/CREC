    public IRubyObject op_or(ThreadContext context, IRubyObject other) {
        if (other instanceof RubyBignum) {
            return bignorm(getRuntime(), value.or(((RubyBignum) other).value));
        }
        if (other instanceof RubyFixnum) { // no bignorm here needed
            return bignorm(getRuntime(), value.or(fix2big((RubyFixnum)other)));
        }
        return coerceBit(context, "|", other);
    }
