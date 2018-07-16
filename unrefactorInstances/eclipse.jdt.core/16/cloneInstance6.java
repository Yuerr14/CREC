public void testSpaceThenFirstParameter() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class Bar {								\n" +
		"	void foo() {							\n" +
		"		this.fred( 1, 2, i);				\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"fred( ",
		// expectedCompletionNodeToString:
		"<CompleteOnMessageSend:this.fred()>",
		// expectedUnitDisplayString:
		"class Bar {\n" + 
		"  Bar() {\n" + 
		"  }\n" + 
		"  void foo() {\n" + 
		"    <CompleteOnMessageSend:this.fred()>;\n" + 
		"  }\n" + 
		"}\n",
		// expectedCompletionIdentifier:
		"",
		// expectedReplacedSource:
		"fred( ",
		// test name
		"<completion just before first parameter with a space after open parenthesis>"
	);
}
