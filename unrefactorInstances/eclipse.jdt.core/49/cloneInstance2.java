	int matchRule) {

	this(findDeclarations,
		findReferences,
		declaringSimpleName,	
		declaringQualification,
		parameterQualifications, 
		parameterSimpleNames,
		matchRule);

	// Set flags
	try {
		this.flags = method.getFlags();
	} catch (JavaModelException e) {
		// do nothing
	}

	// Get unique key for parameterized constructors
	String genericDeclaringTypeSignature = null;
	BindingKey key;
	if (method.isResolved() && (key = new BindingKey(method.getKey())).isParameterizedType()) {
		genericDeclaringTypeSignature = key.getDeclaringTypeSignature();
	} else {
		constructorParameters = true;
	}

	// Store type signature and arguments for declaring type
	if (genericDeclaringTypeSignature != null) {
		this.typeSignatures = Util.splitTypeLevelsSignature(genericDeclaringTypeSignature);
		setTypeArguments(Util.getAllTypeArguments(this.typeSignatures));
	} else {
		storeTypeSignaturesAndArguments(method.getDeclaringType());
	}

	// store type signatures and arguments for method parameters type
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
	constructorArguments = extractMethodArguments(method);
	if (hasConstructorArguments())  ((InternalSearchPattern)this).mustResolve = true;
}
