    public IRubyObject call(ThreadContext context, IRubyObject self, RubyModule klazz, String name, IRubyObject arg1, IRubyObject arg2) {
        Invocation invocation = new Invocation();
        Object[] nativeArgs = new Object[2];
        nativeArgs[0] = marshallers[0].marshal(invocation, arg1);
        nativeArgs[1] = marshallers[1].marshal(invocation, arg2);
        IRubyObject retVal = functionInvoker.invoke(context.getRuntime(), function, nativeArgs);
        invocation.finish();
        return retVal;
    }
