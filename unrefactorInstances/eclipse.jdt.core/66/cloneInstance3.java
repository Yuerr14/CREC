	public void testLocalType2() throws CoreException {
		try {
			createFile(
				"/P/X.java",
				"public class X {\n" +
				"  void foo() {\n" +
				"    class Y {\n" +
				"    }\n" +
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
				"    void foo()\n" + 
				"      class Y\n" + 
				"      class Z", 
				cu);
		} finally {
			deleteFile("/P/X.java");
		}
	}
