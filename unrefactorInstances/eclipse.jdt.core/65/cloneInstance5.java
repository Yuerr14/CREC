public void needToEmulateFieldWriteAccess(FieldBinding field, AstNode location) {
	this.handle(
		IProblem.NeedToEmulateFieldWriteAccess,
		new String[] {new String(field.declaringClass.readableName()), new String(field.name)},
		new String[] {new String(field.declaringClass.shortReadableName()), new String(field.name)},
		location.sourceStart,
		location.sourceEnd);
}
