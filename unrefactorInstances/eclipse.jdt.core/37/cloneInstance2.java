		} else if (sourceType.isLocalType()) {
			if (sourceType.isAnonymousType()) 
			    modifiers |= AccFinal;
			Scope scope = this;
			do {
				switch (scope.kind) {
					case METHOD_SCOPE :
						MethodScope methodScope = (MethodScope) scope;
						if (methodScope.isInsideInitializer()) {
							SourceTypeBinding type = ((TypeDeclaration) methodScope.referenceContext).binding;
			
							// inside field declaration ? check field modifier to see if deprecated
							if (methodScope.initializedField != null) {
									// currently inside this field initialization
								if (methodScope.initializedField.isViewedAsDeprecated() && !sourceType.isDeprecated()){
									modifiers |= AccDeprecatedImplicitly;
								}
							} else {
								if (type.isStrictfp())
									modifiers |= AccStrictfp;
								if (type.isViewedAsDeprecated() && !sourceType.isDeprecated()) 
									modifiers |= AccDeprecatedImplicitly;
							}					
						} else {
							MethodBinding method = ((AbstractMethodDeclaration) methodScope.referenceContext).binding;
							if (method != null){
								if (method.isStrictfp())
									modifiers |= AccStrictfp;
								if (method.isViewedAsDeprecated() && !sourceType.isDeprecated())
									modifiers |= AccDeprecatedImplicitly;
							}
						}
						break;
					case CLASS_SCOPE :
						// local member
						if (enclosingType.isStrictfp())
							modifiers |= AccStrictfp;
						if (enclosingType.isViewedAsDeprecated() && !sourceType.isDeprecated())
							modifiers |= AccDeprecatedImplicitly;
						break;
				}
				scope = scope.parent;
			} while (scope != null);
		}
