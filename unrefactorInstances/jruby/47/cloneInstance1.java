    public static IRubyObject max_by(ThreadContext context, IRubyObject self, final Block block) {
        final Ruby runtime = context.runtime;

        if (!block.isGiven()) return enumeratorizeWithSize(context, self, "max_by", enumSizeFn(context, self));

        final IRubyObject result[] = new IRubyObject[] { runtime.getNil() };
        final ThreadContext localContext = context;

        callEach(runtime, context, self, Arity.OPTIONAL, new BlockCallback() {
            IRubyObject memo = null;
            public IRubyObject call(ThreadContext ctx, IRubyObject[] largs, Block blk) {
                IRubyObject larg = packEnumValues(runtime, largs);
                checkContext(localContext, ctx, "max_by");
                IRubyObject v = block.yield(ctx, larg);

                if (memo == null || RubyComparable.cmpint(ctx, invokedynamic(ctx, v, OP_CMP, memo), v, memo) > 0) {
                    memo = v;
                    result[0] = larg;
                }
                return runtime.getNil();
            }
        });
        return result[0];
    }
