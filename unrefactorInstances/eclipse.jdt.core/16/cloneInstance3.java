public void testBeforeLastParameter() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class Bar {								\n" +
		"	void foo() {							\n" +
		"		this.fred(1, 2, i);					\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"fred(1, 2,",
		// expectedCompletionNodeToString:
		"<CompleteOnMessageSend:this.fred(1, 2)>",
		// expectedUnitDisplayString:
		"class Bar {\n" + 
		"  Bar() {\n" + 
		"  }\n" + 
		"  void foo() {\n" + 
		"    <CompleteOnMessageSend:this.fred(1, 2)>;\n" + 
		"  }\n" + 
		"}\n",
		// expectedCompletionIdentifier:
		"",
		// expectedReplacedSource:
		"fred(1, 2,",
		// test name
		"<completion just before last parameter>"
	);
}
