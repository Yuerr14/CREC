	try {
		project.setOption(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_1);
		ReconcileParticipant participant = new ReconcileParticipant();
		setWorkingCopyContents(
			"package p1;\n" +
			"import p2.*;\n" +
			"public class X {\n" +
			"  public void bar() {\n" +
			"    System.out.println()\n" +
			"  }\n" +
			"}"
		);
		this.workingCopy.reconcile(ICompilationUnit.NO_AST, false, null, null);
		assertDeltas(
			"Unexpected participant delta",
			"<null>",
			participant.delta
		);
	} finally {
