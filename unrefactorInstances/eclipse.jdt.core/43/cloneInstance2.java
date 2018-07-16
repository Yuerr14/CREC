	public Map getCompilerOptions() {
		Map defaultOptions = super.getCompilerOptions();
		defaultOptions.put(CompilerOptions.OPTION_LocalVariableAttribute, CompilerOptions.DO_NOT_GENERATE);
		defaultOptions.put(CompilerOptions.OPTION_LineNumberAttribute, CompilerOptions.DO_NOT_GENERATE);
		defaultOptions.put(CompilerOptions.OPTION_SourceFileAttribute, CompilerOptions.DO_NOT_GENERATE);
		defaultOptions.put(CompilerOptions.OPTION_ReportUnusedLocal, CompilerOptions.WARNING);
		defaultOptions.put(CompilerOptions.OPTION_ReportUnusedImport, CompilerOptions.IGNORE);
		defaultOptions.put(CompilerOptions.OPTION_ReportUnusedParameter, CompilerOptions.WARNING);
		defaultOptions.put(CompilerOptions.OPTION_ReportLocalVariableHiding, CompilerOptions.WARNING);
		defaultOptions.put(CompilerOptions.OPTION_ReportUnusedPrivateMember, CompilerOptions.IGNORE);
		defaultOptions.put(CompilerOptions.OPTION_ReportPossibleAccidentalBooleanAssignment, CompilerOptions.WARNING);
		return defaultOptions;
	}
