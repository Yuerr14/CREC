	if (otherVariable instanceof LocalVariableBinding) {
		this.handle(
			IProblem.FieldHidingLocalVariable,
			new String[] {new String(field.declaringClass.readableName()), new String(field.name) },
			new String[] {new String(field.declaringClass.shortReadableName()), new String(field.name) },
			fieldDecl.sourceStart,
			fieldDecl.sourceEnd);
	} else if (otherVariable instanceof FieldBinding) {
