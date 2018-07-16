public void testAfterFirstParameter() {
	this.runTestCheckMethodParse(
		// compilationUnit:
		"class Bar {								\n" +
		"	void foo() {							\n" +
		"		this.fred(\"abc\" , 2, i);		\n" +
		"	}										\n" +
		"}											\n",
		// completeBehind:
		"fred(\"abc\" ",
		// expectedCompletionNodeToString:
		"<CompleteOnMessageSend:this.fred(\"abc\")>",
		// expectedUnitDisplayString:
		"class Bar {\n" + 
		"  Bar() {\n" + 
		"  }\n" + 
		"  void foo() {\n" + 
		"    <CompleteOnMessageSend:this.fred(\"abc\")>;\n" + 
		"  }\n" + 
		"}\n",
		// expectedCompletionIdentifier:
		"",
		// expectedReplacedSource:
		"fred(\"abc\" ",
		// test name
		"<completion just after first parameter>"
	);
}
