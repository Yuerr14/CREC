public void indirectAccessToStaticField(AstNode location, FieldBinding field){
	this.handle(
		IProblem.IndirectAccessToStaticField,
		new String[] {new String(field.declaringClass.readableName()), new String(field.name)},
		new String[] {new String(field.declaringClass.shortReadableName()), new String(field.name)},
		location.sourceStart,
		location.sourceEnd);
}
