	public void test076a() {
		this.runNegativeTest(
			new String[] {
				"A.java",
				"class A {\n" +
				"	<T, S extends J & I<T>> void foo() { }\n" +
				"	<T, S extends I<T> & J> void foo() { }\n" +
				"}\n" +
				"interface I<TT> {}\n" +
				"interface J {}\n"
			},
			"----------\n" +
			"1. ERROR in A.java (at line 2)\n" +
			"	<T, S extends J & I<T>> void foo() { }\n" +
			"	                             ^^^^^\n" +
			"Duplicate method foo() in type A\n" +
			"----------\n" +
			"2. ERROR in A.java (at line 3)\n" +
			"	<T, S extends I<T> & J> void foo() { }\n" +
			"	                             ^^^^^\n" +
			"Duplicate method foo() in type A\n" +
			"----------\n"
			// <T,S>foo() is already defined in A
		);
	}
