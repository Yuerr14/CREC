	public void test0033() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "", "test0033", "Test.java");
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0, 1);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$

		VariableDeclarationFragment variableDeclarationFragment = this.ast.newVariableDeclarationFragment();
		variableDeclarationFragment.setName(this.ast.newSimpleName("f")); //$NON-NLS-1$
		CastExpression castExpression = this.ast.newCastExpression();
		castExpression.setExpression(this.ast.newSimpleName("d"));//$NON-NLS-1$
		castExpression.setType(this.ast.newPrimitiveType(PrimitiveType.FLOAT));//$NON-NLS-1$
		variableDeclarationFragment.setInitializer(castExpression);
		VariableDeclarationStatement statement = this.ast.newVariableDeclarationStatement(variableDeclarationFragment);
		statement.setModifiers(Modifier.NONE);
		statement.setType(this.ast.newPrimitiveType(PrimitiveType.FLOAT));//$NON-NLS-1$

		assertTrue("Both AST trees should be identical", statement.subtreeMatch(new ASTMatcher(), node));		//$NON-NLS-1$
		checkSourceRange(node, "float f = (float) d;", source); //$NON-NLS-1$
	}	
