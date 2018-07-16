    public static IRubyObject setVariableFallback(VariableSite site, IRubyObject self, IRubyObject value) throws Throwable {
        RubyClass realClass = self.getMetaClass().getRealClass();
        VariableAccessor accessor = realClass.getVariableAccessorForWrite(site.name);

        // return provided value
        MethodHandle returnValue = identity(IRubyObject.class);
        returnValue = dropArguments(returnValue, 0, IRubyObject.class);

        // set variable value and fold by returning value
        MethodHandle setValue = findStatic(accessor.getClass(), "setVariable", methodType(void.class, RubyBasicObject.class, RubyClass.class, int.class, Object.class));
        setValue = explicitCastArguments(setValue, methodType(void.class, IRubyObject.class, RubyClass.class, int.class, IRubyObject.class));
        setValue = insertArguments(setValue, 1, realClass, accessor.getIndex());
        setValue = foldArguments(returnValue, setValue);

        // prepare fallback
        MethodHandle fallback = null;
        if (site.chainCount() > RubyInstanceConfig.MAX_POLY_COUNT) {
            if (RubyInstanceConfig.LOG_INDY_BINDINGS) LOG.info(site.name + "\tset on type " + self.getMetaClass().id + " failed (polymorphic)" + extractSourceInfo(site));
            fallback = findStatic(InvokeDynamicSupport.class, "setVariableFail", methodType(IRubyObject.class, VariableSite.class, IRubyObject.class, IRubyObject.class));
            fallback = fallback.bindTo(site);
            site.setTarget(fallback);
            return (IRubyObject)fallback.invokeWithArguments(self, value);
        } else {
            if (RubyInstanceConfig.LOG_INDY_BINDINGS) LOG.info(site.name + "\tset on type " + self.getMetaClass().id + " added to PIC" + extractSourceInfo(site));
            fallback = site.getTarget();
            site.incrementChainCount();
        }

        // prepare test
        MethodHandle test = findStatic(InvocationLinker.class, "testRealClass", methodType(boolean.class, int.class, IRubyObject.class));
        test = insertArguments(test, 0, accessor.getClassId());
        test = dropArguments(test, 1, IRubyObject.class);

        setValue = guardWithTest(test, setValue, fallback);

        if (RubyInstanceConfig.LOG_INDY_BINDINGS) LOG.info(site.name + "\tset on class " + self.getMetaClass().id + " bound directly" + extractSourceInfo(site));
        site.setTarget(setValue);

        return (IRubyObject)setValue.invokeWithArguments(self, value);
    }
