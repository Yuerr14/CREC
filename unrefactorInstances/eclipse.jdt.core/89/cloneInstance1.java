public void test290() {
	Map options = getCompilerOptions();
	options.put(CompilerOptions.OPTION_ReportUncheckedTypeOperation, CompilerOptions.ERROR);
	options.put(CompilerOptions.OPTION_ReportRawTypeReference, CompilerOptions.ERROR);
	options.put(CompilerOptions.OPTION_SuppressOptionalErrors, CompilerOptions.ENABLED);
	options.put(CompilerOptions.OPTION_ReportUnusedWarningToken, CompilerOptions.ERROR);
	this.runConformTest(
		new String[] {
				"X.java",
				"import java.util.ArrayList;\n" + 
				"class X {\n" + 
				"	@SuppressWarnings(\"rawtypes\")\n" + 
				"	void foo(ArrayList arg) {\n" + 
				"		@SuppressWarnings(\"unchecked\")\n" + 
				"		boolean aa = arg.add(1), bb = arg.add(1);\n" + 
				"		if (bb)\n" + 
				"			System.out.println(\"hi\");\n" + 
				"	}\n" + 
				"}"
		},
		"",
		null,
		true,
		null,
		options,
		null);
}
