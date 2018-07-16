	public SyntheticMethodBinding(MethodBinding overridenMethodToBridge, MethodBinding localTargetMethod) {
		
	    this.declaringClass = localTargetMethod.declaringClass;
	    this.selector = overridenMethodToBridge.selector;
	    this.modifiers = overridenMethodToBridge.modifiers | AccBridge | AccSynthetic;
	    this.modifiers &= ~(AccAbstract | AccNative);
	    this.returnType = overridenMethodToBridge.returnType;
	    this.parameters = overridenMethodToBridge.parameters;
	    this.thrownExceptions = overridenMethodToBridge.thrownExceptions;
	    this.targetMethod = localTargetMethod;
	    this.kind = BridgeMethod;
		SyntheticMethodBinding[] knownAccessMethods = ((SourceTypeBinding)this.declaringClass).syntheticMethods();
		int methodId = knownAccessMethods == null ? 0 : knownAccessMethods.length;
		this.index = methodId;	    
	}
