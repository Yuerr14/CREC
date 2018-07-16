    public IRubyObject op_and(ThreadContext context, IRubyObject other) {
        if (other instanceof RubyBignum) {
            return bignorm(getRuntime(), value.and(((RubyBignum) other).value));
        } else if (other instanceof RubyFixnum) {
            return bignorm(getRuntime(), value.and(fix2big((RubyFixnum)other)));
        }
        return coerceBit(context, "&", other);
    }
