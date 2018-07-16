public void test_nullable_field_9() {
	runNegativeTestWithLibs(
			new String[] {
				"X.java",
				"import org.eclipse.jdt.annotation.*;\n" +
				"public class X {\n" +
				"    @Nullable int i;\n" +
				"}\n"
			},
			null /*customOptions*/,
			"----------\n" + 
			"1. ERROR in X.java (at line 3)\n" + 
			"	@Nullable int i;\n" + 
			"	^^^^^^^^^\n" + 
			"The nullness annotation @Nullable is not applicable for the primitive type int\n" + 
			"----------\n");	
}
