	public void testAnonymousType1() throws CoreException {
		try {
			createFile(
				"/P/X.java",
				"public class X {\n" +
				"  void foo() {\n" +
				"    run(new X() {\n" +
				"    });\n" +
				"  }\n" +
				"  void run(X x) {\n" +
				"  }\n" +
				"}"
			);
			ICompilationUnit cu = getCompilationUnit("/P/X.java");
			assertElementDescendants(
				"Unexpected compilation unit contents",
				"X.java\n" + 
				"  class X\n" + 
				"    void foo()\n" + 
				"      class <anonymous #1>\n" + 
				"    void run(X)",
				cu);
		} finally {
			deleteFile("/P/X.java");
		}
	}
