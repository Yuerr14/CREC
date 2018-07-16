ReferenceBinding[] resolvedExceptionTypesFor(MethodBinding method) {
	ReferenceBinding[] exceptions = method.thrownExceptions;
	if ((method.modifiers & CompilerModifiers.AccUnresolved) == 0)
		return exceptions;

	if (!(method.declaringClass instanceof BinaryTypeBinding))
		return TypeConstants.NoExceptions; // safety check

	for (int i = exceptions.length; --i >= 0;)
		exceptions[i] = BinaryTypeBinding.resolveType(exceptions[i], this.environment, true);
	return exceptions;
}
