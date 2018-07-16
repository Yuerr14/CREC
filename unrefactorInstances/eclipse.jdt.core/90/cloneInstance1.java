public void testBug89686() throws CoreException {
	this.workingCopies = new ICompilationUnit[1];
	this.workingCopies[0] = getWorkingCopy("/JavaSearchBugs/src/b89686/A.java",
		"package b89686;\n" +
		"public enum Color {\n" +
		"    RED, GREEN(), BLUE(17), PINK((1+(1+1))) {/*anon*/};\n" +
		"    Color() {}\n" +
		"    Color(int i) {}\n" +
		"}"
	);
	IType type = this.workingCopies[0].getType("Color");
	IMethod method = type.getMethod("Color", new String[0]);
	search(method, REFERENCES);
	assertSearchResults(
		"src/b89686/A.java b89686.Color.RED [RED] EXACT_MATCH\n" +
		"src/b89686/A.java b89686.Color.GREEN [GREEN()] EXACT_MATCH"
	);
}
