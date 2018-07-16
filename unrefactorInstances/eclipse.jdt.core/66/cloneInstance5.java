	public void testLocalType4() throws CoreException {
		try {
			createFile(
				"/P/X.java",
				"public class X {\n" +
				"  {\n" +
				"      class Y {\n" +
				"      }\n" +
				"  }\n" +
				"  void foo() {\n" +
				"    class Z {\n" +
				"    }\n" +
				"  }\n" +
				"}"
			);
			ICompilationUnit cu = getCompilationUnit("/P/X.java");
			assertElementDescendants(
				"Unexpected compilation unit contents",
				"X.java\n" + 
				"  class X\n" + 
				"    <initializer #1>\n" + 
				"      class Y\n" + 
				"    void foo()\n" + 
				"      class Z",
				cu);
		} finally {
			deleteFile("/P/X.java");
		}
	}
