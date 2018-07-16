public void invalidField(NameReference nameRef, FieldBinding field) {
	int id = IProblem.UndefinedField;
	switch (field.problemId()) {
		case NotFound :
			id = IProblem.UndefinedField;
			break;
		case NotVisible :
			id = IProblem.NotVisibleField;
			break;
		case Ambiguous :
			id = IProblem.AmbiguousField;
			break;
		case NonStaticReferenceInStaticContext :
			id = IProblem.NonStaticFieldFromStaticInvocation;
			break;
		case NonStaticReferenceInConstructorInvocation :
			id = IProblem.InstanceFieldDuringConstructorInvocation;
			break;
		case InheritedNameHidesEnclosingName :
			id = IProblem.InheritedFieldHidesEnclosingName;
			break;
		case ReceiverTypeNotVisible :
			this.handle(
				IProblem.NotVisibleType,
				new String[] {new String(field.declaringClass.leafComponentType().readableName())},
				new String[] {new String(field.declaringClass.leafComponentType().shortReadableName())},
				nameRef.sourceStart,
				nameRef.sourceEnd);
			return;
		case NoError : // 0
		default :
			needImplementation(); // want to fail to see why we were here...
			break;
	}
	String[] arguments = new String[] {new String(field.readableName())};
	this.handle(
		id,
		arguments,
		arguments,
		nameRef.sourceStart,
		nameRef.sourceEnd);
}
