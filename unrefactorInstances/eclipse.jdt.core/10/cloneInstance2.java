	public void testExternalJarChanged() throws CoreException, java.io.IOException {
		IPath projectPath = env.addProject("Project"); //$NON-NLS-1$
		env.addExternalJars(projectPath, Util.getJavaClassLibs());

		IPath root = env.getPackageFragmentRootPath(projectPath, ""); //$NON-NLS-1$
		IPath classTest = env.addClass(root, "p", "X", //$NON-NLS-1$ //$NON-NLS-2$
			"package p;\n"+ //$NON-NLS-1$
			"public class X {\n" + //$NON-NLS-1$
			"  void foo() {\n" + //$NON-NLS-1$
			"    new q.Y().bar();\n" + //$NON-NLS-1$
			"  }\n" + //$NON-NLS-1$
			"}" //$NON-NLS-1$
		);
		String externalJar = Util.getOutputDirectory() + java.io.File.separator + "test.jar"; //$NON-NLS-1$
		Util.createJar(
			new String[] {
				"q/Y.java", //$NON-NLS-1$
				"package q;\n" + //$NON-NLS-1$
				"public class Y {\n" + //$NON-NLS-1$
				"}" //$NON-NLS-1$
			},
			new java.util.HashMap(),
			externalJar
		);
		long lastModified = new java.io.File(externalJar).lastModified();
		env.addExternalJar(projectPath, externalJar);

		// build -> expecting problems
		fullBuild();
		expectingProblemsFor(classTest);

		try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
		}
		// fix jar
		Util.createJar(
			new String[] {
				"q/Y.java", //$NON-NLS-1$
				"package q;\n" + //$NON-NLS-1$
				"public class Y {\n" + //$NON-NLS-1$
				"  public void bar() {\n" + //$NON-NLS-1$
				"  }\n" + //$NON-NLS-1$
				"}" //$NON-NLS-1$
			},
			new java.util.HashMap(),
			externalJar
		);
		new java.io.File(externalJar).setLastModified(lastModified + 1000); // to be sure its different
		// add new class to trigger an incremental build
		env.getProject(projectPath).touch(null);

		// incremental build should notice jar file has changed & do a full build
		incrementalBuild();
		expectingNoProblems();
	}
