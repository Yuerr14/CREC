public void testWithExpressionReceiver() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class Bar {								\n" +
		"	void foo() {							\n" +
		"		bar().fred();						\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"fred(",
		// expectedCompletionNodeToString:
		"<CompleteOnMessageSend:bar().fred()>",
		// expectedUnitDisplayString:
		"class Bar {\n" + 
		"  Bar() {\n" + 
		"  }\n" + 
		"  void foo() {\n" + 
		"    <CompleteOnMessageSend:bar().fred()>;\n" + 
		"  }\n" + 
		"}\n",
		// expectedCompletionIdentifier:
		"",
		// expectedReplacedSource:
		"fred(",
		// test name
		"<completion on method invocation with expression receiver>"
	);
}
