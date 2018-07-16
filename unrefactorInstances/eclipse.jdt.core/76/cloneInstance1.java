public void test_nonnull_field_12() {
	runNegativeTestWithLibs(
		new String[] {
			"X.java",
			"import org.eclipse.jdt.annotation.*;\n" +
			"public class X {\n" +
			"    @NonNull int o = 1;\n" +
			"}\n"
		},
		null /*customOptions*/,
		"----------\n" + 
		"1. ERROR in X.java (at line 3)\n" + 
		"	@NonNull int o = 1;\n" + 
		"	^^^^^^^^\n" + 
		"The nullness annotation @NonNull is not applicable for the primitive type int\n" + 
		"----------\n");
}
