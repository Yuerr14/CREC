	public void testBinaryType() throws JavaModelException {
		IClassFile classFile = getClassFile("P", getExternalJCLPathString("1.5"), "java.lang", "String.class");
		String source = classFile.getSource();
		MarkerInfo markerInfo = new MarkerInfo(source);
		markerInfo.astStarts = new int[] {source.indexOf("public")};
		markerInfo.astEnds = new int[] {source.lastIndexOf('}') + 1};
		ASTNode node = buildAST(markerInfo, classFile);
		IBinding binding = ((TypeDeclaration) node).resolveBinding();
		assertNotNull("No binding", binding);
		IJavaElement element = binding.getJavaElement();
		assertElementEquals(
			"Unexpected Java element",
			"String [in String.class [in java.lang [in "+ getExternalJCLPathString("1.5") + " [in P]]]]",
			element
		);
		assertTrue("Element should exist", element.exists());
	}
