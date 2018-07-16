	public void testBug470666a() throws CoreException, IOException {
		myCreateJavaProject("TestAnnot");
		String lib1Content =
				"package libs;\n" + 
				"\n" +
				"interface Function<T,U> {}\n" +
				"interface Collector<T,A,R> {}\n" +
				"public class Collectors {\n" +
				"	 public static <T, U, A, R>\n" + 
				"    Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper,\n" + 
				"                               Collector<? super U, A, R> downstream) { return null; }\n" +
				"}\n";
		addLibraryWithExternalAnnotations(this.project, "lib1.jar", "annots", new String[] {
				"/UnannotatedLib/libs/Collectors.java",
				lib1Content
			}, null);

		// acquire library AST:
		IType type = this.project.findType("libs.Collectors");
		ICompilationUnit libWorkingCopy = type.getClassFile().getWorkingCopy(this.wcOwner, null);
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(libWorkingCopy);
		parser.setResolveBindings(true);
		parser.setStatementsRecovery(false);
		parser.setBindingsRecovery(false);
		CompilationUnit unit = (CompilationUnit) parser.createAST(null);
		libWorkingCopy.discardWorkingCopy();
		
		// find type binding:
		int start = lib1Content.indexOf("T, ? extends U>"); // bound of type param of method param
		ASTNode name = NodeFinder.perform(unit, start, 0);
		assertTrue("should be simple name", name.getNodeType() == ASTNode.SIMPLE_NAME);
		ASTNode method = name.getParent();
		while (!(method instanceof MethodDeclaration))
			method = method.getParent();
		IMethodBinding methodBinding = ((MethodDeclaration)method).resolveBinding();
		
		// find annotation file (not yet existing):
		IFile annotationFile = ExternalAnnotationUtil.getAnnotationFile(this.project, methodBinding.getDeclaringClass(), null);
		assertFalse("file should not exist", annotationFile.exists());
		assertEquals("file path", "/TestAnnot/annots/libs/Collectors.eea", annotationFile.getFullPath().toString());

		// annotate:
		String originalSignature = ExternalAnnotationUtil.extractGenericSignature(methodBinding);
		String[] annotatedSign = ExternalAnnotationUtil.annotateParameterType(
				originalSignature, 
				"Llibs/Function<-T1T;+TU;>;",  // <- @NonNull T
				0, MergeStrategy.OVERWRITE_ANNOTATIONS);
		assertEquals("dry-run result", "[<T:Ljava/lang/Object;U:Ljava/lang/Object;A:Ljava/lang/Object;R:Ljava/lang/Object;>(, " + 
				"Llibs/Function<-TT;+TU;>;, " + 
				"Llibs/Function<-T1T;+TU;>;, " +  // <- @NonNull T
				"Llibs/Collector<-TU;TA;TR;>;)Llibs/Collector<TT;*TR;>;]",
				Arrays.toString(annotatedSign));
	}
