	public void test0468() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "src", "test0468", "A.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		ASTNode result = runConversion(AST.JLS3, sourceUnit, true);
		CompilationUnit compilationUnit = (CompilationUnit) result;
		ASTNode node = getASTNode(compilationUnit, 0, 1, 0);
		assertEquals("No error", 0, compilationUnit.getProblems().length); //$NON-NLS-1$
		assertNotNull("No node", node);
		assertTrue("not a return statement", node.getNodeType() == ASTNode.RETURN_STATEMENT); //$NON-NLS-1$
		ReturnStatement returnStatement = (ReturnStatement) node;
		Expression expression = returnStatement.getExpression();
		assertNotNull("No expression", expression);
		assertTrue("not a field access", expression.getNodeType() == ASTNode.FIELD_ACCESS); //$NON-NLS-1$
		FieldAccess fieldAccess = (FieldAccess) expression;
		Name name = fieldAccess.getName();
		IBinding binding = name.resolveBinding();
		assertNotNull("No binding", binding);
		assertEquals("Wrong type", IBinding.VARIABLE, binding.getKind());
		IVariableBinding variableBinding = (IVariableBinding) binding;
		assertEquals("Wrong name", "i", variableBinding.getName());
		assertEquals("Wrong type", "int", variableBinding.getType().getName());
		IVariableBinding variableBinding2 = fieldAccess.resolveFieldBinding();
		assertTrue("different binding", variableBinding == variableBinding2);

		node = getASTNode(compilationUnit, 0, 0);
		assertNotNull("No node", node);
		assertEquals("Wrong type", ASTNode.FIELD_DECLARATION, node.getNodeType());
		FieldDeclaration fieldDeclaration = (FieldDeclaration) node;
		List fragments = fieldDeclaration.fragments();
		assertEquals("wrong size", 1, fragments.size());
		VariableDeclarationFragment fragment = (VariableDeclarationFragment) fragments.get(0);

		ASTNode foundNode = compilationUnit.findDeclaringNode(variableBinding);
		assertNotNull("No found node", foundNode);
		assertEquals("wrong node", fragment, foundNode);
	}
