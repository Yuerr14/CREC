	public void testBinaryMethod() throws JavaModelException {
		IClassFile classFile = getClassFile("P", getExternalJCLPathString("1.5"), "java.lang", "Enum.class");
		String source = classFile.getSource();
		MarkerInfo markerInfo = new MarkerInfo(source);
		markerInfo.astStarts = new int[] {source.indexOf("protected Enum")};
		markerInfo.astEnds = new int[] {source.indexOf('}', markerInfo.astStarts[0]) + 1};
		ASTNode node = buildAST(markerInfo, classFile);
		IBinding binding = ((MethodDeclaration) node).resolveBinding();
		assertNotNull("No binding", binding);
		IJavaElement element = binding.getJavaElement();
		assertElementEquals(
			"Unexpected Java element",
			"Enum(java.lang.String, int) [in Enum [in Enum.class [in java.lang [in "+ getExternalJCLPathString("1.5") + " [in P]]]]]",
			element
		);
		assertTrue("Element should exist", element.exists());
	}
