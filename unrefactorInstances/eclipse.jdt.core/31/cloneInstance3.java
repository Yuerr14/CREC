	public void test0031() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "", "test0031", "Test.java");
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0, 1);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$
		VariableDeclarationFragment variableDeclarationFragment = this.ast.newVariableDeclarationFragment();
		variableDeclarationFragment.setName(this.ast.newSimpleName("s")); //$NON-NLS-1$
		CastExpression castExpression = this.ast.newCastExpression();
		castExpression.setExpression(this.ast.newSimpleName("o"));//$NON-NLS-1$
		castExpression.setType(this.ast.newSimpleType(this.ast.newSimpleName("String")));//$NON-NLS-1$
		variableDeclarationFragment.setInitializer(castExpression);
		VariableDeclarationStatement statement = this.ast.newVariableDeclarationStatement(variableDeclarationFragment);
		statement.setModifiers(Modifier.NONE);
		statement.setType(this.ast.newSimpleType(this.ast.newSimpleName("String")));//$NON-NLS-1$
		assertTrue("Both AST trees should be identical", statement.subtreeMatch(new ASTMatcher(), node));		//$NON-NLS-1$
		checkSourceRange(node, "String s = (String) o;", source); //$NON-NLS-1$
	}						
