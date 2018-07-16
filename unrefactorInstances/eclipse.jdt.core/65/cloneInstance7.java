public void unqualifiedFieldAccess(NameReference reference, FieldBinding field) {
	this.handle(
		IProblem.UnqualifiedFieldAccess,
		new String[] {new String(field.declaringClass.readableName()), new String(field.name)},
		new String[] {new String(field.declaringClass.shortReadableName()), new String(field.name)},
		reference.sourceStart,
		reference.sourceEnd);
}
