	public void test0620() throws JavaModelException {
		ICompilationUnit workingCopy = null;
		try {
			String contents =
				"public class X {\n" +
				"	public static void main(String[] args) {\n" +
				"		System.out.println((int) \'\\0\');\n" +
				"		System.out.println((int) \'\\1\');\n" +
				"		System.out.println((int) \'\\2\');\n" +
				"		System.out.println((int) \'\\3\');\n" +
				"		System.out.println((int) \'\\4\');\n" +
				"		System.out.println((int) \'\\5\');\n" +
				"		System.out.println((int) \'\\6\');\n" +
				"		System.out.println((int) \'\\7\');\n" +
				"		System.out.println((int) \'\\077\');\n" +
				"		System.out.println((int) \'\\55\');\n" +
				"		System.out.println((int) \'\\77\');\n" +
				"		System.out.println((int) \'\\377\');\n" +
				"	}\n" +
				"}";
			workingCopy = getWorkingCopy("/Converter/src/X.java", false/*resolve*/);
			ASTNode node = buildAST(
				contents,
				workingCopy,
				false);
			assertEquals("Not a compilation unit", ASTNode.COMPILATION_UNIT, node.getNodeType());
			CompilationUnit unit = (CompilationUnit) node;
			assertProblemsSize(unit, 0);
			unit.accept(new ASTVisitor() {
				public boolean visit(CharacterLiteral characterLiteral) {
					try {
						final String escapedValue = characterLiteral.getEscapedValue();
						final char charValue = characterLiteral.charValue();
						if (escapedValue.equals("\'\\0\'")) {
							assertEquals("Wrong value", 0, charValue);
						} else if (escapedValue.equals("\'\\1\'")) {
							assertEquals("Wrong value", 1, charValue);
						} else if (escapedValue.equals("\'\\2\'")) {
							assertEquals("Wrong value", 2, charValue);
						} else if (escapedValue.equals("\'\\3\'")) {
							assertEquals("Wrong value", 3, charValue);
						} else if (escapedValue.equals("\'\\4\'")) {
							assertEquals("Wrong value", 4, charValue);
						} else if (escapedValue.equals("\'\\5\'")) {
							assertEquals("Wrong value", 5, charValue);
						} else if (escapedValue.equals("\'\\6\'")) {
							assertEquals("Wrong value", 6, charValue);
						} else if (escapedValue.equals("\'\\7\'")) {
							assertEquals("Wrong value", 7, charValue);
						} else if (escapedValue.equals("\'\\077\'")) {
							assertEquals("Wrong value", 63, charValue);
						} else if (escapedValue.equals("\'\\55\'")) {
							assertEquals("Wrong value", 45, charValue);
						} else if (escapedValue.equals("\'\\77\'")) {
							assertEquals("Wrong value", 63, charValue);
						} else if (escapedValue.equals("\'\\377\'")) {
							assertEquals("Wrong value", 255, charValue);
						} else {
							assertTrue("Should not get there", false);
						}
					} catch(IllegalArgumentException e) {
						assertTrue("Should not happen", false);
					}
					return false;
				}
			});
		} finally {
			if (workingCopy != null)
				workingCopy.discardWorkingCopy();
		}
	}
