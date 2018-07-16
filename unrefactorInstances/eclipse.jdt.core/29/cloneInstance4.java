public void test0011(){
	String str =
		"public enum Test {\n" + 
		"	B() {\n" + 
		"	  void foo() {\n" + 
		"	  }\n" + 
		"	},\n" + 
		"	A() {\n" + 
		"	  void foo() {\n" + 
		"	    zzz\n" + 
		"	  }\n" + 
		"	}\n" + 
		"}\n";

	String completeBehind = "zzz";
	int cursorLocation = str.indexOf("zzz") + completeBehind.length() - 1;
	String expectedCompletionNodeToString = "<CompleteOnName:zzz>";
	String expectedParentNodeToString = "<NONE>";
	String completionIdentifier = "zzz";
	String expectedReplacedSource = "zzz";
	String expectedUnitDisplayString =
		"public enum Test {\n" + 
		"  B() {\n" + 
		"    void foo() {\n" + 
		"    }\n" + 
		"  },\n" + 
		"  A() {\n" + 
		"    void foo() {\n" + 
		"      <CompleteOnName:zzz>;\n" + 
		"    }\n" + 
		"  },\n" + 
		"  public Test() {\n" + 
		"  }\n" + 
		"  <clinit>() {\n" + 
		"  }\n" + 
		"}\n";

	checkDietParse(
			str.toCharArray(),
			cursorLocation,
			expectedCompletionNodeToString,
			expectedParentNodeToString,
			expectedUnitDisplayString,
			completionIdentifier,
			expectedReplacedSource,
	"diet ast");
}
