	public void test0211() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "src", "test0211", "Test.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(AST.JLS3, sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$
		assertTrue("The node is not a FieldDeclaration", node instanceof FieldDeclaration); //$NON-NLS-1$
		Javadoc actualJavadoc = ((FieldDeclaration) node).getJavadoc();
		assertTrue("Javadoc must be null", actualJavadoc == null);//$NON-NLS-1$
		checkSourceRange(node, "int i;", source); //$NON-NLS-1$
	}
