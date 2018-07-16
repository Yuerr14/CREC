			public ASTNode wrap() {
				// return VariableDeclarationFragment that embeds x
				VariableDeclarationFragment s1 = ASTTest.this.ast.newVariableDeclarationFragment();
				ClassInstanceCreation s0 = ASTTest.this.ast.newClassInstanceCreation();
				AnonymousClassDeclaration a1 = ASTTest.this.ast.newAnonymousClassDeclaration();
				s0.setAnonymousClassDeclaration(a1);
				s1.setInitializer(s0);
				ForStatement s2 = ASTTest.this.ast.newForStatement();
				s2.initializers().add(x);
				Initializer s3 = ASTTest.this.ast.newInitializer();
				a1.bodyDeclarations().add(s3);
				s3.getBody().statements().add(s2);
				return s1;
			}
