		} if (node instanceof SingleNameReference) {
			SingleNameReference singleNameReference = (SingleNameReference) node;
			if (singleNameReference.isTypeReference()) {
				return this.getTypeBinding(singleNameReference.resolvedType);
			} else {
				// this is a variable or a field
				Binding binding = singleNameReference.binding;
				if (binding != null) {
					if (binding.isValidBinding()) {
						return this.getVariableBinding((org.eclipse.jdt.internal.compiler.lookup.VariableBinding) binding);
					} else {
						/*
						 * http://dev.eclipse.org/bugs/show_bug.cgi?id=24449
						 */
						if (binding instanceof ProblemFieldBinding) {
							ProblemFieldBinding problemFieldBinding = (ProblemFieldBinding) binding;
							switch(problemFieldBinding.problemId()) {
								case ProblemReasons.NotVisible :
								case ProblemReasons.NonStaticReferenceInStaticContext :
								case ProblemReasons.NonStaticReferenceInConstructorInvocation :
									ReferenceBinding declaringClass = problemFieldBinding.declaringClass;
									FieldBinding exactBinding = declaringClass.getField(problemFieldBinding.name, true /*resolve*/);
									if (exactBinding != null) {
										if (exactBinding.type != null) {
											IVariableBinding variableBinding2 = (IVariableBinding) this.bindingTables.compilerBindingsToASTBindings.get(exactBinding);
											if (variableBinding2 != null) {
												return variableBinding2;
											}
											variableBinding2 = new VariableBinding(this, exactBinding);
											this.bindingTables.compilerBindingsToASTBindings.put(exactBinding, variableBinding2);
											return variableBinding2;
										}
									}
									break;
							}
						}
					}
				}
			}
		} else if (node instanceof QualifiedSuperReference) {
