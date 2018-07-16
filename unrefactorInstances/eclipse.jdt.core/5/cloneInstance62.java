	public void test0222() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "src", "test0222", "Test.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$
		Javadoc actualJavadoc = ((Initializer) node).getJavadoc();
		assertNotNull("Javadoc comment should no be null", actualJavadoc); //$NON-NLS-1$
		Javadoc javadoc = this.ast.newJavadoc();
		javadoc.setComment("/** JavaDoc Comment*/");//$NON-NLS-1$*/
		assertTrue("Both AST trees should be identical", javadoc.subtreeMatch(new ASTMatcher(), actualJavadoc));//$NON-NLS-1$
		String expectedContents = 
			 "/** JavaDoc Comment*/\n" + //$NON-NLS-1$
			 "  static {}";//$NON-NLS-1$
		checkSourceRange(node, expectedContents, source); //$NON-NLS-1$
		checkSourceRange(actualJavadoc, "/** JavaDoc Comment*/", source); //$NON-NLS-1$
		
	}
