	public void test0208() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "src", "test0208", "Test.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(AST.JLS3, sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$
		assertTrue("The node is not a MethodDeclaration", node instanceof MethodDeclaration); //$NON-NLS-1$
		Javadoc actualJavadoc = ((MethodDeclaration) node).getJavadoc();
		assertTrue("Javadoc must be null", actualJavadoc == null);//$NON-NLS-1$
		checkSourceRange(node, "void foo(final int i) {}", source); //$NON-NLS-1$
	}
