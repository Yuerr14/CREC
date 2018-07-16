	public void test0149() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "src", "test0149", "Test.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(AST.JLS3, sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$
		Javadoc actualJavadoc = ((Initializer) node).getJavadoc();
		assertNull("Javadoc comment should be null", actualJavadoc); //$NON-NLS-1$
		checkSourceRange(node, "{}", source); //$NON-NLS-1$
	}
