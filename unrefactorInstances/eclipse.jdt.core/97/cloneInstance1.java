public void testEmptyNameReferenceAfterCast() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class X {									\n" +
		"	void foo() {							\n" +
		"		X x = (X)							\n" +
		"											\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"(X)",
		// expectedCompletionNodeToString:
		"<CompleteOnName:>",
		// expectedUnitDisplayString:
		"class X {\n" + 
		"  X() {\n" + 
		"  }\n" + 
		"  void foo() {\n" +
		"    X x = (X) <CompleteOnName:>;\n" + 
		"  }\n" + 
		"}\n",
		// expectedCompletionIdentifier:
		"",
		// expectedReplacedSource:
		"",
		// test name
		"<complete on empty name reference after cast>"
	);
}
