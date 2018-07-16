    protected IRubyObject cacheAndCall(IRubyObject caller, RubyClass selfType, Block block, ThreadContext context, IRubyObject self, IRubyObject arg) {
        CacheEntry entry = selfType.searchWithCache(methodName);
        DynamicMethod method = entry.method;
        if (methodMissing(method, caller)) {
            return callMethodMissing(context, self, method, arg, block);
        }
        updateCache(entry);
        return method.call(context, self, selfType, methodName, arg, block);
    }
