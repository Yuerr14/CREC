public void testZA_2() {
	String str = 
			"package p; \n" + 
			"import java.util.Vector;\n";

	String testName = "<complete on imported package>";
	String completeBehind = "jav";
	String expectedCompletionNodeToString = "<CompleteOnImport:jav>";
	String completionIdentifier = "jav";
	String expectedReplacedSource = "java.util.Vector";
	int cursorLocation = str.indexOf("java.util.Vector") + completeBehind.length() - 1;
	String expectedUnitDisplayString =
		"package p;\n" + 
		"import <CompleteOnImport:jav>;\n";

	checkDietParse(
		str.toCharArray(), 
		cursorLocation, 
		expectedCompletionNodeToString,
		expectedUnitDisplayString,
		completionIdentifier,
		expectedReplacedSource,
		testName); 
}
