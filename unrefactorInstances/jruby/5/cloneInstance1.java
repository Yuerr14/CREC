    public void isMethodBound(String name, BranchCallback trueBranch, BranchCallback falseBranch) {
        metaclass();
        method.ldc(name);
        method.iconst_0(); // push false
        method.invokevirtual(p(RubyClass.class), "isMethodBound", sig(boolean.class, params(String.class, boolean.class)));
        Label falseLabel = new Label();
        Label exitLabel = new Label();
        method.ifeq(falseLabel); // EQ == 0 (i.e. false)
        trueBranch.branch(this);
        method.go_to(exitLabel);
        method.label(falseLabel);
        falseBranch.branch(this);
        method.label(exitLabel);
    }
