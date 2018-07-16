			public boolean visit(AnonymousClassDeclaration anonymousClassDeclaration) {
				List bodyDeclarations = anonymousClassDeclaration.bodyDeclarations();
				for (Iterator iter = bodyDeclarations.iterator(); iter.hasNext();) {
					BodyDeclaration bodyDeclaration = (BodyDeclaration) iter.next();
					bodyDeclaration.setProperty(CompilationUnitSorter.RELATIVE_ORDER, new Integer(bodyDeclaration.getStartPosition()));
					anonymousClassDeclaration.setProperty(CONTAINS_MALFORMED_NODES, Boolean.valueOf(isMalformed(bodyDeclaration)));
				}
				return true;
			}
