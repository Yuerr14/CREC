			public ASTNode wrap() {
				// return VariableDeclarationFragment that embeds x
				VariableDeclarationFragment s1 = ASTTest.this.ast.newVariableDeclarationFragment();
				ClassInstanceCreation s0 = ASTTest.this.ast.newClassInstanceCreation();
				AnonymousClassDeclaration a1 = ASTTest.this.ast.newAnonymousClassDeclaration();
				s0.setAnonymousClassDeclaration(a1);
				s1.setInitializer(s0);
				Initializer s2 = ASTTest.this.ast.newInitializer();
				a1.bodyDeclarations().add(s2);
				s2.getBody().statements().add(x);
				return s1;
			}
