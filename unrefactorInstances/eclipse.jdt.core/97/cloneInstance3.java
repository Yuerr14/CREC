public void testEmptyNameReferenceInArrayDim() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class X {									\n" +
		"	void foo() {							\n" +
		"		int[]								\n" +
		"											\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"int[",
		// expectedCompletionNodeToString:
		"<CompleteOnName:>",
		// expectedUnitDisplayString:
		"class X {\n" + 
		"  X() {\n" + 
		"  }\n" + 
		"  void foo() {\n" + 
		"    <CompleteOnName:>;\n" + 
		"  }\n" + 
		"}\n",
		// expectedCompletionIdentifier:
		"",
		// expectedReplacedSource:
		"",
		// test name
		"<complete on empty name reference in array dim>"
	);
}
