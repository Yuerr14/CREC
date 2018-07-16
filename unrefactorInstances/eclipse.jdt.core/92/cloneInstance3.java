public void testSearchJavaElementConstructorDeclarations() throws CoreException {

	// Wait for indexing end
	AbstractJavaModelTests.waitUntilIndexesReady();

	// Get constructor 'equals'
	IClassFile object = getClassFile(JDT_CORE_PROJECT, "rt.jar", "java.lang", "Object.class");
	IType objectType = object.getType();
	IMethod constructor = objectType.getMethod("Object", new String[0]);
	assertTrue("Cannot find default constructor", constructor != null && constructor.exists());

	// Warm up
	JavaSearchResultCollector resultCollector = new JavaSearchResultCollector();
	search(constructor, DECLARATIONS, resultCollector);
	NumberFormat intFormat = NumberFormat.getIntegerInstance();
	System.out.println("	- "+intFormat.format(resultCollector.count)+" declarations for default constructor in workspace");

	// Measures
	for (int i=0; i<ITERATIONS_COUNT; i++) {
		// clean before test
		cleanCategoryTableCache(false, resultCollector);
		runGc();

		// test
		startMeasuring();
		search(constructor, DECLARATIONS, resultCollector);
		stopMeasuring();
	}
	
	// Commit
	commitMeasurements();
	assertPerformance();
}
