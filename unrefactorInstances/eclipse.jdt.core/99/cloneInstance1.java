	public void test0314() throws JavaModelException {
		ICompilationUnit sourceUnit = getCompilationUnit("Converter" , "src", "test0314", "Test.java"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		char[] source = sourceUnit.getSource().toCharArray();
		ASTNode result = runConversion(sourceUnit, false);
		assertNotNull("No result", result); //$NON-NLS-1$
		assertTrue("Not a compilation unit", result instanceof CompilationUnit); //$NON-NLS-1$
		CompilationUnit compilationUnit = (CompilationUnit) result;
		assertEquals("Wrong line number", 1, compilationUnit.getLineNumber(0)); //$NON-NLS-1$
		// ensure that last character is on the last line
		assertEquals("Wrong line number", 3, compilationUnit.getLineNumber(source.length - 1)); //$NON-NLS-1$
		// source.length is beyond the size of the compilation unit source
		assertEquals("Wrong line number", -1, compilationUnit.getLineNumber(source.length)); //$NON-NLS-1$
	}
