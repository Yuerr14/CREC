public void testSearchJavaElementMethodDeclarations() throws CoreException {

	// Wait for indexing end
	AbstractJavaModelTests.waitUntilIndexesReady();

	// Get method 'equals'
	IClassFile object = getClassFile(JDT_CORE_PROJECT, "rt.jar", "java.lang", "Object.class");
	IType objectType = object.getType();
	IMethod method = objectType.getMethod("equals", new String[] { "Ljava.lang.Object;" });
	assertTrue("Cannot find method equals", method != null && method.exists());

	// Warm up
	JavaSearchResultCollector resultCollector = new JavaSearchResultCollector();
	search(method, DECLARATIONS, resultCollector);
	NumberFormat intFormat = NumberFormat.getIntegerInstance();
	System.out.println("	- "+intFormat.format(resultCollector.count)+" declarations for method '"+method.getElementName()+"' in workspace");

	// Measures
	for (int i=0; i<ITERATIONS_COUNT; i++) {
		// clean before test
		cleanCategoryTableCache(false, resultCollector);
		runGc();

		// test
		startMeasuring();
		search(method, DECLARATIONS, resultCollector);
		stopMeasuring();
	}
	
	// Commit
	commitMeasurements();
	assertPerformance();
}
