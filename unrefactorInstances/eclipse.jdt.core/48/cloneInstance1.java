	public void test0231() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter15" , "src", "test0231", "Test3.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		IType type = sourceUnit.getType("Test3");//$NON-NLS-1$

		assertNotNull("Should not be null", type);
		ASTParser parser= ASTParser.newParser(getJLS3());
		parser.setSource(sourceUnit);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		CompilationUnit unit = (CompilationUnit) parser.createAST(null);
		List types = unit.types();
		TypeDeclaration typeDeclaration = (TypeDeclaration) types.get(0);
		ITypeBinding typeBinding = typeDeclaration.resolveBinding();
		StringBuffer buffer = new StringBuffer();
		while (typeBinding != null) {
			buffer.append(typeBinding.getAnnotations().length);
			typeBinding= typeBinding.getSuperclass();
		}
		assertEquals("Wrong number of annotations", "020", String.valueOf(buffer));
	}
