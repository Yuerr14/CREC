public void testReconcileParticipant02() throws CoreException {
	ReconcileParticipant participant = new ReconcileParticipant(){
		public boolean isActive(IJavaProject project) {
			return false;
		}
	};
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
}
