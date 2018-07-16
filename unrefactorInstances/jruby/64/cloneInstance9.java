    protected IRubyObject cacheAndCall(IRubyObject caller, RubyClass selfType, Block block, ThreadContext context, IRubyObject self, IRubyObject arg1, IRubyObject arg2, IRubyObject arg3) {
        CacheEntry entry = selfType.searchWithCache(methodName);
        DynamicMethod method = entry.method;
        if (methodMissing(method, caller)) {
            return callMethodMissing(context, self, method, arg1, arg2, arg3, block);
        }
        updateCache(entry);
        return method.call(context, self, selfType, methodName, arg1, arg2, arg3, block);
    }
