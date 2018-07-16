	public void testExternalJarChange() throws JavaModelException, IOException {
		// setup
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
		String externalJar = Util.getOutputDirectory() + File.separator + "test.jar"; //$NON-NLS-1$
		Util.createJar(
			new String[] {
				"q/Y.java", //$NON-NLS-1$
				"package q;\n" + //$NON-NLS-1$
				"public class Y {\n" + //$NON-NLS-1$
				"}" //$NON-NLS-1$
			},
			new HashMap(),
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
			new HashMap(),
			externalJar
		);
		
		new java.io.File(externalJar).setLastModified(lastModified + 1000); // to be sure its different
		// refresh project and rebuild -> expecting no problems
		IJavaProject project = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot().getProject("Project")); //$NON-NLS-1$
		project.getJavaModel().refreshExternalArchives(new IJavaElement[] {project}, null);
		incrementalBuild();
		expectingNoProblems();
		
	}
