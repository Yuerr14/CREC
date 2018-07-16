public void testBug95152_wc02() throws CoreException {
	this.workingCopies = new ICompilationUnit[1];
	this.workingCopies[0] = getWorkingCopy("/JavaSearchBugs/src/b95152/T.java",
		"package b95152;\n" +
		"public class T {\n" +
		"	T2 c2;\n" +
		"	T2.T3 c3;\n" +
		"	T(int c) {\n" +
		"		c2 = new T2(c);\n" +
		"		c3 = c2.new T3(c);\n" +
		"	}\n" +
		"	class T2 {\n" +
		"		T2(int x) {}\n" +
		"		class T3 {\n" +
		"			T3(int x) {}\n" +
		"		}\n" +
		"	}\n" +
		"}\n"
	);
	// search constructor first level member
	IType type = this.workingCopies[0].getType("T").getType("T2");
	search(type.getMethods()[0], REFERENCES);
	// search constructor second level member
	type = type.getType("T3");
	search(type.getMethods()[0], REFERENCES);
	// verify searches results
	assertSearchResults(
		"src/b95152/T.java b95152.T(int) [new T2(c)] EXACT_MATCH\n" +
		"src/b95152/T.java b95152.T(int) [c2.new T3(c)] EXACT_MATCH"
	);
}
