	public void test057() {
		this.runNegativeTest(
			new String[] {
				"X.java",
				"public class X {\n"
					+ "	/**\n"
					+ "	 * @throws java.awt.AWTexception Invalid exception: unknown type\n"
					+ "	 * @throws IOException Invalid exception: unknown type\n"
					+ "	 */\n"
					+ "	public void t_foo() throws InvalidException {\n"
					+ "	}\n"
					+ "}\n" },
			"----------\n"
				+ "1. ERROR in X.java (at line 3)\n"
				+ "	* @throws java.awt.AWTexception Invalid exception: unknown type\n"
				+ "	          ^^^^^^^^^^^^^^^^^^^^^\n"
				+ "Javadoc: java.awt.AWTexception cannot be resolved to a type\n"
				+ "----------\n"
				+ "2. ERROR in X.java (at line 4)\n"
				+ "	* @throws IOException Invalid exception: unknown type\n"
				+ "	          ^^^^^^^^^^^\n"
				+ "Javadoc: IOException cannot be resolved to a type\n"
				+ "----------\n"
				+ "3. ERROR in X.java (at line 6)\n"
				+ "	public void t_foo() throws InvalidException {\n"
				+ "	                           ^^^^^^^^^^^^^^^^\n"
				+ "InvalidException cannot be resolved to a type\n"
				+ "----------\n");
	}
