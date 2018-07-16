	public void _test0348() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter15" , "src", "test0348", "AnnotatedInterfaceWithStringDefault.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		IType type = sourceUnit.getType("AnnotatedInterfaceWithStringDefault");//$NON-NLS-1$
		//ICompilationUnit sourceUnit2 = getCompilationUnit("Converter15" , "src", "test0348", "TestAnnotationWithStringDefault.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		//IType type2 = sourceUnit2.getType("TestAnnotationWithStringDefault");//$NON-NLS-1$

		assertNotNull("Should not be null", type);
		ASTParser parser= ASTParser.newParser(AST.JLS4);
		parser.setProject(type.getJavaProject());
		IBinding[] bindings= parser.createBindings(new IJavaElement[] { type }, null);
		if (bindings.length == 1 && bindings[0] instanceof ITypeBinding) {
			ITypeBinding typeBinding= (ITypeBinding) bindings[0];
			IAnnotationBinding[] annotations = typeBinding.getAnnotations();
			for (int i = 0, max = annotations.length; i < max; i++) {
				IAnnotationBinding annotation = annotations[i];
				IMemberValuePairBinding[] allMemberValuePairs = annotation.getAllMemberValuePairs();
				for (int j = 0, max2 = allMemberValuePairs.length; j < max2; j++) {
					IMemberValuePairBinding memberValuePair = allMemberValuePairs[j];
					Object defaultValue = memberValuePair.getValue();
					System.out.println(defaultValue);
					assertNotNull("no default value", defaultValue);
				}
			}
		}
	}
