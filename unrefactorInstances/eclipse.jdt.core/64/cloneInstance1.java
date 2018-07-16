	public void testCollapsedTargetNodes() throws Exception {
		IPackageFragment pack1= this.sourceFolder.createPackageFragment("test1", false, null);
		
		StringBuffer buf= new StringBuffer();
		buf.append("package test1;\n");
		buf.append("public class E {\n");
		buf.append("    public void foo(Object o) {\n");
		buf.append("        return;\n");
		buf.append("    }\n");
		buf.append("}\n");
		ICompilationUnit cu= pack1.createCompilationUnit("E.java", buf.toString(), false, null);

		CompilationUnit astRoot= createAST(cu);
		ASTRewrite rewrite= ASTRewrite.create(astRoot.getAST());
		
		AST ast= astRoot.getAST();
		
		TypeDeclaration type= findTypeDeclaration(astRoot, "E");
		MethodDeclaration methodDecl= findMethodDeclaration(type, "foo");
		
		ReturnStatement returnStatement= (ReturnStatement) methodDecl.getBody().statements().get(0);
		
		MethodInvocation newMethodInv1= ast.newMethodInvocation();
		newMethodInv1.setName(ast.newSimpleName("foo1"));
		ExpressionStatement st1= ast.newExpressionStatement(newMethodInv1);
		
		MethodInvocation newMethodInv2= ast.newMethodInvocation();
		newMethodInv2.setName(ast.newSimpleName("foo2"));
		ExpressionStatement st2= ast.newExpressionStatement(newMethodInv2);
		
		ASTNode placeholder= rewrite.createGroupNode(new Statement[] { st1, st2 });
		rewrite.replace(returnStatement, placeholder, null);
			
		String preview= evaluateRewrite(cu, rewrite); 
		
		buf= new StringBuffer();
		buf.append("package test1;\n");
		buf.append("public class E {\n");
		buf.append("    public void foo(Object o) {\n");
		buf.append("        foo1();\n");
		buf.append("        foo2();\n");
		buf.append("    }\n");
		buf.append("}\n");
		String expected= buf.toString();		

		assertEqualString(preview, expected);
	}
