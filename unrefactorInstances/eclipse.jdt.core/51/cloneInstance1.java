			public boolean visit(AnnotationTypeDeclaration annotationTypeDeclaration) {
				List bodyDeclarations = annotationTypeDeclaration.bodyDeclarations();
				for (Iterator iter = bodyDeclarations.iterator(); iter.hasNext();) {
					BodyDeclaration bodyDeclaration = (BodyDeclaration) iter.next();
					bodyDeclaration.setProperty(CompilationUnitSorter.RELATIVE_ORDER, new Integer(bodyDeclaration.getStartPosition()));
					annotationTypeDeclaration.setProperty(CONTAINS_MALFORMED_NODES, Boolean.valueOf(isMalformed(bodyDeclaration)));
				}
				return true;
			}
