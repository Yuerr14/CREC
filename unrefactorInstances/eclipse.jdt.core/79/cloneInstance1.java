	public void testFieldAccess() {
		FieldAccess x1 = ast.newFieldAccess();
		x1.setExpression(E1);
		x1.setName(N1);
		TestVisitor v1 = new TestVisitor();
		b.setLength(0);
		x1.accept(v1);
		String result = b.toString();
		assertTrue(result.equals("[(eFA"+E1S+N1S+"eFA)]")); //$NON-NLS-1$ //$NON-NLS-2$
	}
