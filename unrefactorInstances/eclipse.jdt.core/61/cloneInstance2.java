public void testSearchPatternCreation17() {
	IMethod method = getCompilationUnit("/P/x/y/z/Foo.java").getType("Foo").getMethod("bar", new String[] {});
	SearchPattern searchPattern = createPattern(
			method, 
			IJavaSearchConstants.REFERENCES);
	
	assertPattern(
		"MethodReferencePattern: x.y.z.Foo.bar() --> void, exact match, case sensitive, erasure only",
		searchPattern);
}
