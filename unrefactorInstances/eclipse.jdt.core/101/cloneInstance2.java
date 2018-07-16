					} else {
						SyntheticAccessMethodBinding accessor =
							syntheticReadAccessors == null
								? null
								: syntheticReadAccessors[syntheticReadAccessors.length - 1];
						if (accessor == null) {
							if (lastFieldBinding.isStatic()) {
								codeStream.getstatic(lastFieldBinding);
							} else {
								codeStream.getfield(lastFieldBinding);
							}
						} else {
							codeStream.invokestatic(accessor);
						}
						codeStream.generateImplicitConversion(implicitConversion);
						TypeBinding requiredGenericCast = getGenericCast(this.otherCodegenBindings == null ? 0 : this.otherCodegenBindings.length);
						if (requiredGenericCast != null) codeStream.checkcast(requiredGenericCast);
					}
