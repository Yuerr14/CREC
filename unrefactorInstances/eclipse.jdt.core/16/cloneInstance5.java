public void testNoReceiver() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class Bar {								\n" +
		"	void foo() {							\n" +
		"		fred();								\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"fred(",
		// expectedCompletionNodeToString:
		"<CompleteOnMessageSend:fred()>",
		// expectedUnitDisplayString:
		"class Bar {\n" + 
		"  Bar() {\n" + 
		"  }\n" + 
		"  void foo() {\n" + 
		"    <CompleteOnMessageSend:fred()>;\n" + 
		"  }\n" + 
		"}\n",
		// expectedCompletionIdentifier:
		"",
		// expectedReplacedSource:
		"fred(",
		// test name
		"<completion on method invocation with no receiver>"
	);
}
