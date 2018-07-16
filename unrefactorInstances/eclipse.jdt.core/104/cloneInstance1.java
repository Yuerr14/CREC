	public void testBug96237_Private02() {
		this.reportInvalidJavadocVisibility = CompilerOptions.PRIVATE;
		runNegativeTest(
			new String[] {
				"comment6/Invalid.java",
				"package comment6;\n" + 
				"public class Invalid {\n" + 
				"    /**\n" + 
				"     * @see Inner\n" + 
				"     */\n" + 
				"    public class Inner { }\n" + 
				"}\n" + 
				"/**\n" + 
				" * See also {@link Inner} \n" + 
				" */\n" + 
				"class Sub1 extends Invalid { }\n"
			},
			//comment6\Invalid.java:6: warning - Tag @see: reference not found: Inner
			//comment6\Invalid.java:11: warning - Tag @link: reference not found: Inner
			"----------\n" + 
			"1. ERROR in comment6\\Invalid.java (at line 4)\n" + 
			"	* @see Inner\n" + 
			"	       ^^^^^\n" + 
			"Javadoc: Invalid member type qualification\n" + 
			"----------\n" + 
			"2. ERROR in comment6\\Invalid.java (at line 9)\n" + 
			"	* See also {@link Inner} \n" + 
			"	                  ^^^^^\n" + 
			"Javadoc: Invalid member type qualification\n" + 
			"----------\n"
		);
	}
