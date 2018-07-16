			if (needFieldInitializations) {
				if (!preInitSyntheticFields){
					generateSyntheticFieldInitializationsIfNecessary(scope, codeStream, declaringClass);
				}
				// generate user field initialization
				if (declaringType.fields != null) {
					for (int i = 0, max = declaringType.fields.length; i < max; i++) {
						FieldDeclaration fieldDecl;
						if (!(fieldDecl = declaringType.fields[i]).isStatic()) {
							fieldDecl.generateCode(initializerScope, codeStream);
						}
					}
				}
			}
