	public void test0072() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "", "test0072", "Test.java");
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0, 1);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$
		VariableDeclarationFragment variableDeclarationFragment = this.ast.newVariableDeclarationFragment();
		variableDeclarationFragment.setName(this.ast.newSimpleName("b1")); //$NON-NLS-1$
		PrefixExpression prefixExpression = this.ast.newPrefixExpression();
		prefixExpression.setOperator(PrefixExpression.Operator.NOT);
		prefixExpression.setOperand(this.ast.newSimpleName("b"));//$NON-NLS-1$
		variableDeclarationFragment.setInitializer(prefixExpression);
		VariableDeclarationStatement statement = this.ast.newVariableDeclarationStatement(variableDeclarationFragment);
		statement.setModifiers(Modifier.NONE);
		statement.setType(this.ast.newPrimitiveType(PrimitiveType.BOOLEAN));

		assertTrue("Both AST trees should be identical", statement.subtreeMatch(new ASTMatcher(), node));		//$NON-NLS-1$
		checkSourceRange(node, "boolean b1 = !b;", source); //$NON-NLS-1$
	}	
