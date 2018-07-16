    public InterpreterContext(IRScope scope, Callable<List<Instr>> instructions) throws Exception {
        this.instructionsCallback = instructions;
        this.scope = scope;

        this.metaClassBodyScope = scope instanceof IRMetaClassBody;
        this.temporaryVariablecount = scope.getTemporaryVariablesCount();
        this.hasExplicitCallProtocol = scope.getFlags().contains(IRFlags.HAS_EXPLICIT_CALL_PROTOCOL);
        // FIXME: Centralize this out of InterpreterContext
        this.reuseParentDynScope = scope.getFlags().contains(IRFlags.REUSE_PARENT_DYNSCOPE);
        this.pushNewDynScope = !scope.getFlags().contains(IRFlags.DYNSCOPE_ELIMINATED) && !reuseParentDynScope;
        this.popDynScope = this.pushNewDynScope || this.reuseParentDynScope;
        this.receivesKeywordArguments = scope.getFlags().contains(IRFlags.RECEIVES_KEYWORD_ARGS);
    }
