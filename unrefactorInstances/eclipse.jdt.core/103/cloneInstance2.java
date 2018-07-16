public void testBug156491b() throws CoreException {
	resultCollector.showRule = true;
	setUpBug156491();
	IMethod method = workingCopies[0].getType("L1").getMethod("test", new String[0]);
	this.resultCollector.showFlavors = PatternLocator.SUPER_INVOCATION_FLAVOR;
	search(method, REFERENCES);
	assertSearchResults(
		"src/other/Test.java void other.Test.testInterface(I) [test()] EXACT_MATCH SUPER INVOCATION\n" + 
		"src/other/Test.java void other.Test.testSuperInvocation(L1) [test()] EXACT_MATCH\n" + 
		"src/other/Test.java void other.Test.testInvocation(L2) [test()] EXACT_MATCH"
	);
}
