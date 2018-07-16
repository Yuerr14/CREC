	public SyntheticAccessMethodBinding(MethodBinding overridenMethodToBridge, MethodBinding localTargetMethod) {
	    this.declaringClass = localTargetMethod.declaringClass;
	    this.selector = overridenMethodToBridge.selector;
	    this.modifiers = overridenMethodToBridge.modifiers | AccBridge | AccSynthetic;
	    this.modifiers &= ~(AccAbstract | AccNative);
	    this.returnType = overridenMethodToBridge.returnType;
	    this.parameters = overridenMethodToBridge.parameters;
	    this.thrownExceptions = overridenMethodToBridge.thrownExceptions;
	    this.targetMethod = localTargetMethod;
	    this.accessType = BridgeMethodAccess;
		SyntheticAccessMethodBinding[] knownAccessMethods = ((SourceTypeBinding)this.declaringClass).syntheticAccessMethods();
		int methodId = knownAccessMethods == null ? 0 : knownAccessMethods.length;
		this.index = methodId;	    
	}
