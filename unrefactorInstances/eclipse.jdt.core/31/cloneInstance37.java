	public void test0089() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "", "test0089", "Test.java");
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		ASTNode node = getASTNode((CompilationUnit) result, 0, 0, 0);
		assertNotNull("Expression should not be null", node); //$NON-NLS-1$

		VariableDeclarationFragment variableDeclarationFragment = this.ast.newVariableDeclarationFragment();
		variableDeclarationFragment.setName(this.ast.newSimpleName("s")); //$NON-NLS-1$

		VariableDeclarationStatement statement = this.ast.newVariableDeclarationStatement(variableDeclarationFragment);
		QualifiedName name = 
			this.ast.newQualifiedName(
				this.ast.newQualifiedName(
					this.ast.newSimpleName("java"),//$NON-NLS-1$
					this.ast.newSimpleName("lang")//$NON-NLS-1$
				),
				this.ast.newSimpleName("String") //$NON-NLS-1$
			);
		statement.setType(this.ast.newSimpleType(name));
		statement.setModifiers(Modifier.NONE);

		assertTrue("Both AST trees should be identical", statement.subtreeMatch(new ASTMatcher(), node));		//$NON-NLS-1$
		checkSourceRange(node, "java.lang.String s;", source); //$NON-NLS-1$
	}
