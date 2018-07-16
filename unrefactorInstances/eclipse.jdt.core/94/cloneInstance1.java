public void wildcardInvocation(ASTNode location, TypeBinding receiverType, MethodBinding method, TypeBinding[] arguments) {
	TypeBinding offendingArgument = null;
	TypeBinding offendingParameter = null;
	for (int i = 0, length = method.parameters.length; i < length; i++) {
		TypeBinding parameter = method.parameters[i];
		if (parameter.isWildcard() && (((WildcardBinding) parameter).boundKind != Wildcard.SUPER)) {
			offendingParameter = parameter;
			offendingArgument = arguments[i];
			break;
		}
	}
	
    if (method.isConstructor()) {
		this.handle(
			IProblem.WildcardConstructorInvocation,
			new String[] {
				new String(receiverType.sourceName()),
				typesAsString(method.isVarargs(), method.parameters, false),
				new String(receiverType.readableName()),
				typesAsString(false, arguments, false),
				new String(offendingArgument.readableName()),
				new String(offendingParameter.readableName()),
			 }, 
			new String[] {
				new String(receiverType.sourceName()),
				typesAsString(method.isVarargs(), method.parameters, true),
				new String(receiverType.shortReadableName()),
				typesAsString(false, arguments, true),
				new String(offendingArgument.shortReadableName()),
				new String(offendingParameter.shortReadableName()),
			 }, 
			location.sourceStart,
			location.sourceEnd);    
    } else {
		this.handle(
			IProblem.WildcardMethodInvocation,
			new String[] {
				new String(method.selector),
				typesAsString(method.isVarargs(), method.parameters, false),
				new String(receiverType.readableName()),
				typesAsString(false, arguments, false),
				new String(offendingArgument.readableName()),
				new String(offendingParameter.readableName()),
			 }, 
			new String[] {
				new String(method.selector),
				typesAsString(method.isVarargs(), method.parameters, true),
				new String(receiverType.shortReadableName()),
				typesAsString(false, arguments, true),
				new String(offendingArgument.shortReadableName()),
				new String(offendingParameter.shortReadableName()),
			 }, 
			location.sourceStart,
			location.sourceEnd);    
    }
}
