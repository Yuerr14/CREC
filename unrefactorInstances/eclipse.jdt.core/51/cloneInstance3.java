			public boolean visit(TypeDeclaration typeDeclaration) {
				List bodyDeclarations = typeDeclaration.bodyDeclarations();
				for (Iterator iter = bodyDeclarations.iterator(); iter.hasNext();) {
					BodyDeclaration bodyDeclaration = (BodyDeclaration) iter.next();
					bodyDeclaration.setProperty(CompilationUnitSorter.RELATIVE_ORDER, new Integer(bodyDeclaration.getStartPosition()));
					typeDeclaration.setProperty(CONTAINS_MALFORMED_NODES, Boolean.valueOf(isMalformed(bodyDeclaration)));
				}
				return true;
			}
