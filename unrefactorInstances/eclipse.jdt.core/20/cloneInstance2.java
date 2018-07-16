	public void _test0679() throws JavaModelException {
		ICompilationUnit workingCopy = null;
		try {
			String contents =
				"public class X {\n" +
				"	int i = 1 - 2 + 3 + 4 + 5;\n" +
				"}";
			workingCopy = getWorkingCopy("/Converter/src/X.java", true/*resolve*/);
			ASTNode node = buildAST(
				contents,
				workingCopy);
			assertEquals("Not a compilation unit", ASTNode.COMPILATION_UNIT, node.getNodeType());
			CompilationUnit unit = (CompilationUnit) node;
			assertProblemsSize(unit, 0);
			node = getASTNode(unit, 0, 0);
			assertEquals("Not a field declaration", ASTNode.FIELD_DECLARATION, node.getNodeType());
			FieldDeclaration fieldDeclaration = (FieldDeclaration) node;
			final List fragments = fieldDeclaration.fragments();
			assertEquals("Wrong size", 1, fragments.size());
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) fragments.get(0);
			final Expression initializer = fragment.getInitializer();
			assertEquals("Not an infix expression", ASTNode.INFIX_EXPRESSION, initializer.getNodeType());
			InfixExpression infixExpression = (InfixExpression) initializer;
			final Expression leftOperand = infixExpression.getLeftOperand();
			assertEquals("Not a number literal", ASTNode.NUMBER_LITERAL, leftOperand.getNodeType());
			NumberLiteral literal = (NumberLiteral) leftOperand;
			assertEquals("Wrong value", "1", literal.getToken());
		} finally {
			if (workingCopy != null)
				workingCopy.discardWorkingCopy();
		}
	}
