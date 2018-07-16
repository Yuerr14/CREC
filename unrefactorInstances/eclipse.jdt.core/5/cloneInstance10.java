	public void test0141() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "src", "test0141", "Test.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(AST.JLS3, sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$
		assertTrue("The node is not a TypeDeclaration", node instanceof TypeDeclaration); //$NON-NLS-1$
		Javadoc actualJavadoc = ((TypeDeclaration) node).getJavadoc();
		String expectedContents = 
			 "/** JavaDoc Comment*/\n" + //$NON-NLS-1$
			 "  class B {}";//$NON-NLS-1$
		checkSourceRange(node, expectedContents, source); //$NON-NLS-1$
		checkSourceRange(actualJavadoc, "/** JavaDoc Comment*/", source); //$NON-NLS-1$
	}
