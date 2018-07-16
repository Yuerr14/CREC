    public static IRubyObject methodMissing(ThreadContext context, IRubyObject recv, String name, Visibility lastVis, CallType lastCallType, IRubyObject[] args, Block block) {
        Ruby runtime = context.runtime;
        RubySymbol symbol = runtime.newSymbol(name);

        // create a lightweight thunk
        IRubyObject msg = new RubyNameError.RubyNameErrorMessage(runtime,
                                                                 recv,
                                                                 symbol,
                                                                 lastVis,
                                                                 lastCallType);
        final IRubyObject[]exArgs;
        final RubyClass exc;
        if (lastCallType != CallType.VARIABLE) {
            exc = runtime.getNoMethodError();
            exArgs = new IRubyObject[]{msg, symbol, RubyArray.newArrayNoCopy(runtime, args)};
        } else {
            exc = runtime.getNameError();
            exArgs = new IRubyObject[]{msg, symbol};
        }

        throw new RaiseException((RubyException)exc.newInstance(context, exArgs, Block.NULL_BLOCK));
    }
