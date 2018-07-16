    public IRubyObject op_xor(ThreadContext context, IRubyObject other) {
        if (other instanceof RubyBignum) {
            return bignorm(getRuntime(), value.xor(((RubyBignum) other).value));
        }
        if (other instanceof RubyFixnum) {
            return bignorm(getRuntime(), value.xor(BigInteger.valueOf(((RubyFixnum) other).getLongValue())));
        }
        return coerceBit(context, "^", other);
    }
