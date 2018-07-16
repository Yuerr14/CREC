public void test016(){
	String str =
		"public class Bar {\n" +
		"  void foo() {\n" +
		"    Object o = new Object[0];\n" +
		"    foo();\n" +
		"  }\n" +
		"}\n";

	String testName = "<bug 39502>";
	String completeBehind = "foo(";
	String expectedCompletionNodeToString = "<CompleteOnMessageSend:foo()>";
	String completionIdentifier = "";
	String expectedReplacedSource = "foo(";
	int cursorLocation = str.lastIndexOf("foo(") + completeBehind.length() - 1;
	String expectedUnitDisplayString =
		"public class Bar {\n" +
		"  public Bar() {\n" +
		"  }\n" +
		"  void foo() {\n" +
		"    Object o;\n" +
		"    <CompleteOnMessageSend:foo()>;\n" +
		"  }\n" +
		"}\n";

	checkMethodParse(
		str.toCharArray(),
		cursorLocation,
		expectedCompletionNodeToString,
		expectedUnitDisplayString,
		completionIdentifier,
		expectedReplacedSource,
		testName);
}
