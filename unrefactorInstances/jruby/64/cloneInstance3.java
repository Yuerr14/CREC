    protected IRubyObject cacheAndCall(IRubyObject caller, RubyClass selfType, Block block, ThreadContext context, IRubyObject self) {
        CacheEntry entry = selfType.searchWithCache(methodName);
        DynamicMethod method = entry.method;
        if (methodMissing(method, caller)) {
            return callMethodMissing(context, self, method, block);
        }
        updateCache(entry);
        return method.call(context, self, selfType, methodName, block);
    }
