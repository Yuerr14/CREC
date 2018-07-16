	public void test0057() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "", "test0057", "Test.java");
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0, 2);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$

		VariableDeclarationFragment variableDeclarationFragment = this.ast.newVariableDeclarationFragment();
		variableDeclarationFragment.setName(this.ast.newSimpleName("n")); //$NON-NLS-1$
		InfixExpression infixExpression = this.ast.newInfixExpression();
		infixExpression.setLeftOperand(this.ast.newSimpleName("i")); //$NON-NLS-1$
		infixExpression.setRightOperand(this.ast.newSimpleName("j")); //$NON-NLS-1$
		infixExpression.setOperator(InfixExpression.Operator.PLUS);
		variableDeclarationFragment.setInitializer(infixExpression);
		VariableDeclarationStatement statement = this.ast.newVariableDeclarationStatement(variableDeclarationFragment);
		statement.setModifiers(Modifier.NONE);
		statement.setType(this.ast.newPrimitiveType(PrimitiveType.INT));

		assertTrue("Both AST trees should be identical", statement.subtreeMatch(new ASTMatcher(), node));		//$NON-NLS-1$
		checkSourceRange(node, "int n = i + j;", source); //$NON-NLS-1$
	}	
