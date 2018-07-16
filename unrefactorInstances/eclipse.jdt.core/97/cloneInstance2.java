public void testEmptyNameReferenceAfterPlus() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class X {									\n" +
		"	void foo() {							\n" +
		"		1 + 								\n" +
		"											\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"1 +",
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
		"<complete on empty name reference after + operator>"
	);
}
