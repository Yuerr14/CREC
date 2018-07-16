            IRubyObject arg1, IRubyObject arg2, IRubyObject arg3) {
        Invocation invocation = new Invocation();
        Object[] nativeArgs = new Object[3];
        nativeArgs[0] = marshallers[0].marshal(invocation, arg1);
        nativeArgs[1] = marshallers[1].marshal(invocation, arg2);
        nativeArgs[2] = marshallers[2].marshal(invocation, arg3);
        IRubyObject retVal = functionInvoker.invoke(context.getRuntime(), function, nativeArgs);
        invocation.finish();
        return retVal;
    }
