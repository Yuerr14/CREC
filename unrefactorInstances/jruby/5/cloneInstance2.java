    public void isGlobalDefined(String name, BranchCallback trueBranch, BranchCallback falseBranch) {
        loadRuntime();
        invokeRuby("getGlobalVariables", sig(GlobalVariables.class));
        method.ldc(name);
        method.invokevirtual(p(GlobalVariables.class), "isDefined", sig(boolean.class, params(String.class)));
        Label falseLabel = new Label();
        Label exitLabel = new Label();
        method.ifeq(falseLabel); // EQ == 0 (i.e. false)
        trueBranch.branch(this);
        method.go_to(exitLabel);
        method.label(falseLabel);
        falseBranch.branch(this);
        method.label(exitLabel);
    }
