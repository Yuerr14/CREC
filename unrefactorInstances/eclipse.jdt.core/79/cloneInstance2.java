	public void testVariableDeclarationFragment() {
		VariableDeclarationFragment x1 = ast.newVariableDeclarationFragment();
		x1.setName(N1);
		x1.setInitializer(E1);
		TestVisitor v1 = new TestVisitor();
		b.setLength(0);
		x1.accept(v1);
		String result = b.toString();
		assertTrue(result.equals("[(VS"+N1S+E1S+"VS)]")); //$NON-NLS-1$ //$NON-NLS-2$
	}
