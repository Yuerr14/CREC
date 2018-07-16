			} else if (index == indexOfFirstFieldBinding) {
				if (qualifiedNameReference.isTypeReference()) {
					return this.getTypeBinding(qualifiedNameReference.resolvedType);
				} else {
					Binding binding = qualifiedNameReference.binding;
					if (binding != null) {
						if (binding.isValidBinding()) {
							return this.getVariableBinding((org.eclipse.jdt.internal.compiler.lookup.VariableBinding) binding);
						} else  if (binding instanceof ProblemFieldBinding) {
							ProblemFieldBinding problemFieldBinding = (ProblemFieldBinding) binding;
							switch(problemFieldBinding.problemId()) {
								case ProblemReasons.NotVisible :
								case ProblemReasons.NonStaticReferenceInStaticContext :
									ReferenceBinding declaringClass = problemFieldBinding.declaringClass;
									if (declaringClass != null) {
										FieldBinding exactBinding = declaringClass.getField(tokens[tokens.length - 1], true /*resolve*/);
										if (exactBinding != null) {
											if (exactBinding.type != null) {
												IVariableBinding variableBinding = (IVariableBinding) this.bindingTables.compilerBindingsToASTBindings.get(exactBinding);
												if (variableBinding != null) {
													return variableBinding;
												}
												variableBinding = new VariableBinding(this, exactBinding);
												this.bindingTables.compilerBindingsToASTBindings.put(exactBinding, variableBinding);
												return variableBinding;
											}
										}
									}
									break;
							}
						}
					}
				}
			} else {
