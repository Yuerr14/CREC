public void invalidField(FieldReference fieldRef, TypeBinding searchedType) {
	int id = IProblem.UndefinedField;
	FieldBinding field = fieldRef.binding;
	switch (field.problemId()) {
		case NotFound :
			id = IProblem.UndefinedField;
/* also need to check that the searchedType is the receiver type
			if (searchedType.isHierarchyInconsistent())
				severity = SecondaryError;
*/
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
				IProblem.NotVisibleType, // cannot occur in javadoc comments
				new String[] {new String(searchedType.leafComponentType().readableName())},
				new String[] {new String(searchedType.leafComponentType().shortReadableName())},
				fieldRef.receiver.sourceStart,
				fieldRef.receiver.sourceEnd);
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
		fieldRef.sourceStart,
		fieldRef.sourceEnd);
}
