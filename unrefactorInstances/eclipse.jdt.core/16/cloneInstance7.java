public void testSuper() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class Bar {								\n" +
		"	void foo() {							\n" +
		"		super.fred(1, 2, i);				\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"fred(",
		// expectedCompletionNodeToString:
		"<CompleteOnMessageSend:super.fred()>",
		// expectedUnitDisplayString:
		"class Bar {\n" + 
		"  Bar() {\n" + 
		"  }\n" + 
		"  void foo() {\n" + 
		"    <CompleteOnMessageSend:super.fred()>;\n" + 
		"  }\n" + 
		"}\n",
		// expectedCompletionIdentifier:
		"",
		// expectedReplacedSource:
		"fred(",
		// test name
		"<completion on super method invocation>"
	);
}
