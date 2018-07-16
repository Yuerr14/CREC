public void testSearchPatternCreation18() {
	IMethod method = getCompilationUnit("/P/x/y/z/Foo.java").getType("Foo").getMethod("bar", new String[] {});
	SearchPattern searchPattern = createPattern(
			method, 
			IJavaSearchConstants.ALL_OCCURRENCES);
	
	assertPattern(
		"MethodCombinedPattern: x.y.z.Foo.bar() --> void, exact match, case sensitive, erasure only",
		searchPattern);
}
