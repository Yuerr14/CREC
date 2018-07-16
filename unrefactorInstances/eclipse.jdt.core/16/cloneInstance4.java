public void testBeforeSecondParameter() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class Bar {								\n" +
		"	void foo() {							\n" +
		"		this.fred(1, 2, i);					\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"fred(1, ",
		// expectedCompletionNodeToString:
		"<CompleteOnMessageSend:this.fred(1)>",
		// expectedUnitDisplayString:
		"class Bar {\n" + 
		"  Bar() {\n" + 
		"  }\n" + 
		"  void foo() {\n" + 
		"    <CompleteOnMessageSend:this.fred(1)>;\n" + 
		"  }\n" + 
		"}\n",
		// expectedCompletionIdentifier:
		"",
		// expectedReplacedSource:
		"fred(1, ",
		// test name
		"<completion just before second parameter>"
	);
}
