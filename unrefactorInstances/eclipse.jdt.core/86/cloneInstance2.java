		{
			EnumConstantDeclaration enumConst= (EnumConstantDeclaration) type.enumConstants().get(2);
			assertNull(enumConst.getAnonymousClassDeclaration());
		
			rewrite.remove((ASTNode) enumConst.arguments().get(0), null);
			
			AnonymousClassDeclaration classDecl= ast.newAnonymousClassDeclaration();
			ListRewrite bodyRewrite= rewrite.getListRewrite(classDecl, AnonymousClassDeclaration.BODY_DECLARATIONS_PROPERTY);
			bodyRewrite.insertFirst(createNewMethod(ast, "test", false), null);
			
			rewrite.set(enumConst, EnumConstantDeclaration.ANONYMOUS_CLASS_DECLARATION_PROPERTY, classDecl, null);
		}
