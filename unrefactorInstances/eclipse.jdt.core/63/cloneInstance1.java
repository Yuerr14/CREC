		if (docCommentSupport.equals(JavaCore.ENABLED)) {
			// Verify  method javadoc
			ASTNode node = getASTNode(compilUnit, 0, 0);
			assertEquals("Invalid type for node: "+node, ASTNode.METHOD_DECLARATION, node.getNodeType());
			MethodDeclaration methodDeclaration = (MethodDeclaration) node;
			assertEquals("Invalid method name", "foo", methodDeclaration.getName().toString());
			Javadoc methodJavadoc = methodDeclaration.getJavadoc();
			assertNotNull("MethodDeclaration have a javadoc comment", methodJavadoc);
			int javadocStart = methodJavadoc.getStartPosition();
			assertEquals("Method declaration should include javadoc comment", methodDeclaration.getStartPosition(), javadocStart);
			// Verify method first leading and last trailing comment
			int index = compilUnit.firstLeadingCommentIndex(methodDeclaration);
			assertEquals("Invalid first leading comment for "+methodDeclaration, 1, index);
			index = compilUnit.lastTrailingCommentIndex(methodDeclaration);
			assertEquals("Invalid last trailing comment for "+methodDeclaration, 7, index);
		}
