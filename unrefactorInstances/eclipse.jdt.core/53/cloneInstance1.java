				} else {
					/*
					 * http://dev.eclipse.org/bugs/show_bug.cgi?id=24449
					 */
					if (variableBinding instanceof ProblemFieldBinding) {
						ProblemFieldBinding problemFieldBinding = (ProblemFieldBinding) variableBinding;
						switch(problemFieldBinding.problemId()) {
							case ProblemReasons.NotVisible :
							case ProblemReasons.NonStaticReferenceInStaticContext :
							case ProblemReasons.NonStaticReferenceInConstructorInvocation :
								ReferenceBinding declaringClass = problemFieldBinding.declaringClass;
								FieldBinding exactBinding = declaringClass.getField(problemFieldBinding.name, true /*resolve*/);
								if (exactBinding != null) {
									IVariableBinding variableBinding2 = (IVariableBinding) this.bindingTables.compilerBindingsToASTBindings.get(exactBinding);
									if (variableBinding2 != null) {
										return variableBinding2;
									}
									variableBinding2 = new VariableBinding(this, exactBinding);
									this.bindingTables.compilerBindingsToASTBindings.put(exactBinding, variableBinding2);
									return variableBinding2;
								}
								break;
						}
					}
				}
