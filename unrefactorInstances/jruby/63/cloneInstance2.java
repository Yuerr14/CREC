    public boolean fastIsConstantDefined19(String internedName, boolean inherit) {
        assert internedName.equals(internedName.intern()) : internedName + " is not interned";
        assert IdUtil.isConstant(internedName);

        for (RubyModule module = this; module != null; module = module.getSuperClass()) {
            Object value;
            if ((value = module.constantTableFetch(internedName)) != null) {
                if (value != UNDEF) return true;
                return getAutoloadMap().get(internedName) != null;
            }
            if (!inherit) {
                break;
            }
        }

        return false;
    }
