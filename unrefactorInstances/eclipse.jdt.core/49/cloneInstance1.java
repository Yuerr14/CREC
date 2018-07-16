	int matchRule) {

	this(findDeclarations,
		findReferences,
		selector, 
		declaringQualification,
		declaringSimpleName,	
		returnQualification, 
		returnSimpleName,
		parameterQualifications, 
		parameterSimpleNames,
		method.getDeclaringType(),
		matchRule);
	
	// Set flags
	try {
		this.flags = method.getFlags();
	} catch (JavaModelException e) {
		// do nothing
	}

	// Get unique key for parameterized constructors
	String genericDeclaringTypeSignature = null;
//	String genericSignature = null;
	BindingKey key;
	if (method.isResolved() && (key = new BindingKey(method.getKey())).isParameterizedType()) {
		genericDeclaringTypeSignature = key.getDeclaringTypeSignature();
	} else {
		methodParameters = true;
	}

	// Store type signature and arguments for declaring type
	if (genericDeclaringTypeSignature != null) {
		this.typeSignatures = Util.splitTypeLevelsSignature(genericDeclaringTypeSignature);
		setTypeArguments(Util.getAllTypeArguments(this.typeSignatures));
	} else {
		storeTypeSignaturesAndArguments(declaringType);
	}

	// Store type signatures and arguments for return type
	if (returnSignature != null) {
		returnTypeSignatures = Util.splitTypeLevelsSignature(returnSignature);
		returnTypeArguments = Util.getAllTypeArguments(returnTypeSignatures);
	}

	// Store type signatures and arguments for method parameters type
	if (parameterSignatures != null) {
		int length = parameterSignatures.length;
		if (length > 0) {
			parametersTypeSignatures = new char[length][][];
			parametersTypeArguments = new char[length][][][];
			for (int i=0; i<length; i++) {
				parametersTypeSignatures[i] = Util.splitTypeLevelsSignature(parameterSignatures[i]);
				parametersTypeArguments[i] = Util.getAllTypeArguments(parametersTypeSignatures[i]);
			}
		}
	}

	// Store type signatures and arguments for method
	methodArguments = extractMethodArguments(method);
	if (hasMethodArguments())  ((InternalSearchPattern)this).mustResolve = true;
}
