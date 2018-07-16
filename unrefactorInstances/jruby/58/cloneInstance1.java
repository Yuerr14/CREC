    public InterpreterContext(IRScope scope, List<Instr> instructions) {
        this.scope = scope;

        // FIXME: Hack null instructions means coming from FullInterpreterContext but this should be way cleaner
        // For impl testing - engine = determineInterpreterEngine(scope);
        setEngine(instructions == null ? DEFAULT_INTERPRETER : STARTUP_INTERPRETER);

        this.metaClassBodyScope = scope instanceof IRMetaClassBody;
        this.temporaryVariablecount = scope.getTemporaryVariablesCount();
        this.instructions = instructions != null ? prepareBuildInstructions(instructions) : null;
        this.hasExplicitCallProtocol = scope.getFlags().contains(IRFlags.HAS_EXPLICIT_CALL_PROTOCOL);
        // FIXME: Centralize this out of InterpreterContext
        this.reuseParentDynScope = scope.getFlags().contains(IRFlags.REUSE_PARENT_DYNSCOPE);
        this.pushNewDynScope = !scope.getFlags().contains(IRFlags.DYNSCOPE_ELIMINATED) && !reuseParentDynScope;
        this.popDynScope = this.pushNewDynScope || this.reuseParentDynScope;
        this.receivesKeywordArguments = scope.getFlags().contains(IRFlags.RECEIVES_KEYWORD_ARGS);
    }
