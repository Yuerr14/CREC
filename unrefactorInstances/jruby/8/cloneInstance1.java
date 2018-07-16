    public static IRubyObject getVariableFallback(VariableSite site, IRubyObject self) throws Throwable {
        RubyClass realClass = self.getMetaClass().getRealClass();
        VariableAccessor accessor = realClass.getVariableAccessorForRead(site.name);
        
        // produce nil if the variable has not been initialize
        MethodHandle nullToNil = findStatic(Helpers.class, "nullToNil", methodType(IRubyObject.class, IRubyObject.class, IRubyObject.class));
        nullToNil = insertArguments(nullToNil, 1, self.getRuntime().getNil());
        nullToNil = explicitCastArguments(nullToNil, methodType(IRubyObject.class, Object.class));
        
        // get variable value and filter with nullToNil
        MethodHandle getValue = findStatic(VariableAccessor.class, "getVariable", methodType(Object.class, RubyBasicObject.class, int.class));
        getValue = explicitCastArguments(getValue, methodType(Object.class, IRubyObject.class, int.class));
        getValue = insertArguments(getValue, 1, accessor.getIndex());
        getValue = filterReturnValue(getValue, nullToNil);
        
        // prepare fallback
        MethodHandle fallback = null;
        if (site.chainCount() > RubyInstanceConfig.MAX_POLY_COUNT) {
            if (RubyInstanceConfig.LOG_INDY_BINDINGS) LOG.info(site.name + "\tqet on type " + self.getMetaClass().id + " failed (polymorphic)" + extractSourceInfo(site));
            fallback = findStatic(InvokeDynamicSupport.class, "getVariableFail", methodType(IRubyObject.class, VariableSite.class, IRubyObject.class));
            fallback = fallback.bindTo(site);
            site.setTarget(fallback);
            return (IRubyObject)fallback.invokeWithArguments(self);
        } else {
            if (RubyInstanceConfig.LOG_INDY_BINDINGS) LOG.info(site.name + "\tget on type " + self.getMetaClass().id + " added to PIC" + extractSourceInfo(site));
            fallback = site.getTarget();
            site.incrementChainCount();
        }
        
        // prepare test
        MethodHandle test = findStatic(InvocationLinker.class, "testRealClass", methodType(boolean.class, int.class, IRubyObject.class));
        test = insertArguments(test, 0, accessor.getClassId());
        
        getValue = guardWithTest(test, getValue, fallback);
        
        if (RubyInstanceConfig.LOG_INDY_BINDINGS) LOG.info(site.name + "\tget on class " + self.getMetaClass().id + " bound directly" + extractSourceInfo(site));
        site.setTarget(getValue);
        
        return (IRubyObject)getValue.invokeWithArguments(self);
    }
