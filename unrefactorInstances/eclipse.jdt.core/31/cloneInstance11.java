	public void test0039() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "", "test0039", "Test.java");
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0, 0);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$

		VariableDeclarationFragment variableDeclarationFragment = this.ast.newVariableDeclarationFragment();
		variableDeclarationFragment.setName(this.ast.newSimpleName("c")); //$NON-NLS-1$
		TypeLiteral typeLiteral = this.ast.newTypeLiteral();
		typeLiteral.setType(this.ast.newPrimitiveType(PrimitiveType.VOID));
		variableDeclarationFragment.setInitializer(typeLiteral);
		VariableDeclarationStatement statement = this.ast.newVariableDeclarationStatement(variableDeclarationFragment);
		statement.setModifiers(Modifier.NONE);
		statement.setType(this.ast.newSimpleType(this.ast.newSimpleName("Class")));//$NON-NLS-1$

		assertTrue("Both AST trees should be identical", statement.subtreeMatch(new ASTMatcher(), node));		//$NON-NLS-1$
		checkSourceRange(((VariableDeclarationFragment)((VariableDeclarationStatement)node).fragments().get(0)).getInitializer(), "void.class", source); //$NON-NLS-1$
	}	
