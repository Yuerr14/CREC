	public void test0070() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "", "test0070", "Test.java");
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0, 1);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$
		VariableDeclarationFragment variableDeclarationFragment = this.ast.newVariableDeclarationFragment();
		variableDeclarationFragment.setName(this.ast.newSimpleName("b")); //$NON-NLS-1$
		InstanceofExpression instanceOfExpression = this.ast.newInstanceofExpression();
		instanceOfExpression.setLeftOperand(this.ast.newSimpleName("o"));//$NON-NLS-1$ 
		SimpleType simpleType = this.ast.newSimpleType(this.ast.newSimpleName("Integer"));//$NON-NLS-1$
		instanceOfExpression.setRightOperand(simpleType); 
		variableDeclarationFragment.setInitializer(instanceOfExpression);
		VariableDeclarationStatement statement = this.ast.newVariableDeclarationStatement(variableDeclarationFragment);
		statement.setModifiers(Modifier.NONE);
		statement.setType(this.ast.newPrimitiveType(PrimitiveType.BOOLEAN));

		assertTrue("Both AST trees should be identical", statement.subtreeMatch(new ASTMatcher(), node));		//$NON-NLS-1$
		checkSourceRange(node, "boolean b = o instanceof Integer;", source); //$NON-NLS-1$
	}	
