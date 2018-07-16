public void testZA_3() {
	String str = 
			"package p; \n" + 
			"import java.util.Vector;\n";

	String testName = "<complete on imported type>";
	String completeBehind = "java.util.V";
	String expectedCompletionNodeToString = "<CompleteOnImport:java.util.V>";
	String completionIdentifier = "V";
	String expectedReplacedSource = "java.util.Vector";
	int cursorLocation = str.indexOf(completeBehind) + completeBehind.length() - 1;
	String expectedUnitDisplayString =
		"package p;\n" + 
		"import <CompleteOnImport:java.util.V>;\n";

	checkDietParse(
		str.toCharArray(), 
		cursorLocation, 
		expectedCompletionNodeToString,
		expectedUnitDisplayString,
		completionIdentifier,
		expectedReplacedSource,
		testName); 
}
