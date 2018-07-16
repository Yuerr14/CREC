	public void testJavadocFieldReferenceWithJavadoc() throws CoreException {
		IType type = getCompilationUnit("JavaSearch", "src", "j1", "JavadocSearched.java").getType("JavadocSearched");
		IField field = type.getField("javadocSearchedVar");
		setJavadocOptions();
		search(
				field,
				REFERENCES,
				getJavaSearchScope(),
				this.resultCollector);
		assertSearchResults(
				"src/j1/JavadocInvalidRef.java void j1.JavadocInvalidRef.invalid() [javadocSearchedVar] POTENTIAL_MATCH INSIDE_JAVADOC\n" +
				"src/j1/JavadocInvalidRef.java void j1.JavadocInvalidRef.invalid() [javadocSearchedVar] POTENTIAL_MATCH INSIDE_JAVADOC\n" +
				"src/j1/JavadocValidRef.java void j1.JavadocValidRef.valid() [javadocSearchedVar] EXACT_MATCH INSIDE_JAVADOC",
				this.resultCollector);
	}
