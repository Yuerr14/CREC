    public static CallSite fixnumOperatorBootstrap(Lookup lookup, String name, MethodType type, long value, String file, int line) throws NoSuchMethodException, IllegalAccessException {
        String[] names = name.split(":");
        String operator = JavaNameMangler.demangleMethodName(names[1]);
        JRubyCallSite site = new JRubyCallSite(lookup, type, CallType.NORMAL, file, line, operator, true);

        MethodHandle target = lookup.findStatic(MathLinker.class, "fixnumOperator",
                methodType(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, JRubyCallSite.class, long.class));
        target = insertArguments(target, 3, site, value);

        site.setTarget(target);
        return site;
    }
