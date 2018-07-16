	public void testLocalType1() throws CoreException {
		try {
			createFile(
				"/P/X.java",
				"public class X {\n" +
				"  void foo() {\n" +
				"    class Y {\n" +
				"    }\n" +
				"  }\n" +
				"}"
			);
			ICompilationUnit cu = getCompilationUnit("/P/X.java");
			assertElementDescendants(
				"Unexpected compilation unit contents",
				"X.java\n" + 
				"  class X\n" + 
				"    void foo()\n" + 
				"      class Y",
				cu);
		} finally {
			deleteFile("/P/X.java");
		}
	}
