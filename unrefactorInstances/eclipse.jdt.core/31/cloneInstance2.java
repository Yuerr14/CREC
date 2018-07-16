	public void test0014() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "", "test0014", "Test.java");
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0, 0);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$
		VariableDeclarationFragment variableDeclarationFragment = this.ast.newVariableDeclarationFragment();
		variableDeclarationFragment.setName(this.ast.newSimpleName("i")); //$NON-NLS-1$
		variableDeclarationFragment.setInitializer(this.ast.newNumberLiteral("0"));//$NON-NLS-1$
		VariableDeclarationStatement statement = this.ast.newVariableDeclarationStatement(variableDeclarationFragment);
		statement.setModifiers(Modifier.NONE);
		statement.setType(this.ast.newPrimitiveType(PrimitiveType.INT));

		assertTrue("Both AST trees should be identical", statement.subtreeMatch(new ASTMatcher(), node));		//$NON-NLS-1$
		checkSourceRange(node, "int i = 0;", source); //$NON-NLS-1$
	}
