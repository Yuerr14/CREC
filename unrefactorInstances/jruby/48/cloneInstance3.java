    public static CallSite floatOperatorBootstrap(Lookup lookup, String name, MethodType type, double value, String file, int line) throws NoSuchMethodException, IllegalAccessException {
        String[] names = name.split(":");
        String operator = JavaNameMangler.demangleMethodName(names[1]);
        JRubyCallSite site = new JRubyCallSite(lookup, type, CallType.NORMAL, file, line, operator, true);

        MethodHandle target = lookup.findStatic(MathLinker.class, "floatOperator",
                methodType(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, JRubyCallSite.class, double.class));
        target = insertArguments(target, 3, site, value);

        site.setTarget(target);
        return site;
    }
