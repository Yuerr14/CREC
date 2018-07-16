    protected static IRubyObject methodMissingDirect(ThreadContext context, IRubyObject recv, RubySymbol symbol, Visibility lastVis, CallType lastCallType, IRubyObject[] args, Block block) {
        Ruby runtime = context.runtime;
        
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
            exArgs = new IRubyObject[]{msg, symbol, RubyArray.newArrayNoCopy(runtime, args, 1)};
        } else {
            exc = runtime.getNameError();
            exArgs = new IRubyObject[]{msg, symbol};
        }

        throw new RaiseException((RubyException)exc.newInstance(context, exArgs, Block.NULL_BLOCK));
    }
