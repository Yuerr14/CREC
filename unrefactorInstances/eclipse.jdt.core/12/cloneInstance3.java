	public void test061() {
		this.runNegativeTest(
			new String[] {
				"X.java",
				"public class X {\n"
					+ "	/**\n"
					+ "	 * @throws /IllegalArgumenException.. Invalid exception: invalid class name\n"
					+ "	 * @exception .IllegalArgumen..Exception.. Invalid exception: invalid class name\n"
					+ "	 */\n"
					+ "	public void t_foo() throws IllegalAccessException {\n"
					+ "	}\n"
					+ "}\n" },
			"----------\n"
				+ "1. ERROR in X.java (at line 3)\n"
				+ "	* @throws /IllegalArgumenException.. Invalid exception: invalid class name\n"
				+ "	   ^^^^^^\n"
				+ "Javadoc: Missing class name\n"
				+ "----------\n"
				+ "2. ERROR in X.java (at line 4)\n"
				+ "	* @exception .IllegalArgumen..Exception.. Invalid exception: invalid class name\n"
				+ "	            ^^\n"
				+ "Javadoc: Invalid class name\n"
				+ "----------\n"
				+ "3. ERROR in X.java (at line 6)\n"
				+ "	public void t_foo() throws IllegalAccessException {\n"
				+ "	                           ^^^^^^^^^^^^^^^^^^^^^^\n"
				+ "Javadoc: Missing tag for declared exception IllegalAccessException\n"
				+ "----------\n");
	}
