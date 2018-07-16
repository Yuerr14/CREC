public void test289() {
	Map options = getCompilerOptions();
	options.put(CompilerOptions.OPTION_ReportUncheckedTypeOperation, CompilerOptions.ERROR);
	options.put(CompilerOptions.OPTION_ReportRawTypeReference, CompilerOptions.IGNORE);
	options.put(CompilerOptions.OPTION_SuppressOptionalErrors, CompilerOptions.ENABLED);
	this.runConformTest(
		new String[] {
				"X.java",
				"import java.util.ArrayList;\n" + 
				"\n" + 
				"public class X {\n" + 
				"	void foo(ArrayList arg) {\n" + 
				"		for (\n" + 
				"			@Deprecated\n" + 
				"			@Other\n" + 
				"			@SuppressWarnings(\"unchecked\")\n" +
				"			boolean a= arg.add(1), b= arg.add(1);\n" + 
				"			Boolean.FALSE;\n" + 
				"		) {\n" + 
				"			System.out.println(a && b);\n" + 
				"		}\n" + 
				"	}\n" + 
				"}",
				"Other.java",
				"@interface Other {}"
		},
		"",
		null,
		true,
		null,
		options,
		null);
}
