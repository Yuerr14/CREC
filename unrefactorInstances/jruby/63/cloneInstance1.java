    public boolean fastIsConstantDefined(String internedName) {
        assert internedName.equals(internedName.intern()) : internedName + " is not interned";
        assert IdUtil.isConstant(internedName);
        boolean isObject = this == getRuntime().getObject();

        RubyModule module = this;

        do {
            Object value;
            if ((value = module.constantTableFetch(internedName)) != null) {
                if (value != UNDEF) return true;
                return getAutoloadMap().get(internedName) != null;
            }

        } while (isObject && (module = module.getSuperClass()) != null );

        return false;
    }
