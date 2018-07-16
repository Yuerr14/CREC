				if (!ignoreDeclaringType) {
					IType declaringClass = method.getDeclaringType();
					declaringSimpleName = declaringClass.getElementName().toCharArray();
					declaringQualification = declaringClass.getPackageFragment().getElementName().toCharArray();
					char[][] enclosingNames = enclosingTypeNames(declaringClass);
					if (enclosingNames.length > 0) {
						declaringQualification = CharOperation.concat(declaringQualification, CharOperation.concatWith(enclosingNames, '.'), '.');
					}
				}
