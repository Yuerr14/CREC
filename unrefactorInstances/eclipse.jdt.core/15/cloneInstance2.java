public void testBug151500b() throws Exception {	
	try {
		IJavaProject p = createJavaProject("P", new String[] {"src"}, new String[]{"JCL15_LIB", "/P/lib151500.jar"}, "bin", "1.4");
		createJar(
				new String[] {
						"foo/Foo.java",
						"package foo;\n" +
						"public class Foo {\n"+
						"  public Foo(int p1) {}\n"+
						"  public Bar bar = new Bar(1,2);\n"+
						"  public class Bar {\n" +
						"    int param1;\n" +
						"	 int param2;\n" +
						"	 public Bar (int a, int b) {\n" +
						"		param1 = a;\n" +
						"		param2 = b;\n" +
						"	 }\n" +
						"	 public void someMethod(String paramName) {}\n"+
						"  }\n"+
						"}"
				},
				p.getProject().getLocation().append("lib151500.jar").toOSString(),
				new String[]{getExternalJCLPathString("1.3")},
				"1.3");
		
		refresh(p);
		
		waitUntilIndexesReady();
		
		this.workingCopies = new ICompilationUnit[1];
		this.workingCopies[0] = getWorkingCopy(
				"/P/src/test/Test.java",
				"package test;\n"+
				"public class Test {\n" +
				"  void m() {\n" +
				"    new foo.Foo(1).new B;\n" +
				"  }\n" +
				"}");

		// do completion
		CompletionTestsRequestor2 requestor = new CompletionTestsRequestor2(true, false, false, true, true);
		requestor.allowAllRequiredProposals();
		NullProgressMonitor monitor = new NullProgressMonitor();

	    String str = this.workingCopies[0].getSource();
	    String completeBehind = "new foo.Foo(1).new B";
	    int cursorLocation = str.lastIndexOf(completeBehind) + completeBehind.length();
	    this.workingCopies[0].codeComplete(cursorLocation, requestor, this.wcOwner, monitor);
	    
	    assertResults(
	    		"Bar[CONSTRUCTOR_INVOCATION]{(), Lfoo.Foo$Bar;, (II)V, Bar, (a, b), " + (R_DEFAULT + R_RESOLVED + R_INTERESTING + R_CASE + R_NON_RESTRICTED + R_UNQUALIFIED + R_CONSTRUCTOR) + "}\n" +
	    		"   Foo.Bar[TYPE_REF]{Bar, foo, Lfoo.Foo$Bar;, null, null, " + (R_DEFAULT + R_RESOLVED + R_INTERESTING + R_CASE + R_NON_RESTRICTED + R_UNQUALIFIED + R_CONSTRUCTOR) + "}",
			requestor.getResults());
	} finally {
		deleteProject("P");
	}
}
