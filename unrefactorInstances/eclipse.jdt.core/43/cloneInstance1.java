	protected Map getCompilerOptions() {
		Map defaultOptions = super.getCompilerOptions();
		defaultOptions.put(CompilerOptions.OPTION_LocalVariableAttribute, CompilerOptions.GENERATE);
		defaultOptions.put(CompilerOptions.OPTION_ReportUnusedPrivateMember, CompilerOptions.WARNING);
		defaultOptions.put(CompilerOptions.OPTION_ReportLocalVariableHiding, CompilerOptions.WARNING);
		defaultOptions.put(CompilerOptions.OPTION_ReportFieldHiding, CompilerOptions.WARNING);
		defaultOptions.put(CompilerOptions.OPTION_ReportPossibleAccidentalBooleanAssignment, CompilerOptions.WARNING);
		defaultOptions.put(CompilerOptions.OPTION_ReportSyntheticAccessEmulation, CompilerOptions.WARNING);
		defaultOptions.put(CompilerOptions.OPTION_PreserveUnusedLocal, CompilerOptions.PRESERVE);
		defaultOptions.put(CompilerOptions.OPTION_PreserveUnusedLocal, CompilerOptions.PRESERVE);
		defaultOptions.put(CompilerOptions.OPTION_ReportUnnecessaryElse, CompilerOptions.WARNING );
		return defaultOptions;
	}
