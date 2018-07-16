	public void test0469() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "src", "codeManipulation", "bug.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		ASTNode result = runConversion(AST.JLS3, sourceUnit, true);
		CompilationUnit compilationUnit = (CompilationUnit) result;
		ASTNode node = getASTNode(compilationUnit, 0, 2, 0);
		assertEquals("No error", 0, compilationUnit.getProblems().length); //$NON-NLS-1$
		assertNotNull("No node", node);
		assertTrue("not a variable declaration statement", node.getNodeType() == ASTNode.VARIABLE_DECLARATION_STATEMENT); //$NON-NLS-1$
		ASTNode parent = node.getParent();
		assertNotNull(parent);
		assertTrue("not a block", parent.getNodeType() == ASTNode.BLOCK); //$NON-NLS-1$
	}
