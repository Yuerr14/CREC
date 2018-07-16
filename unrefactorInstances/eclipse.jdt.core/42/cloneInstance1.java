ReferenceBinding[] resolvedExceptionTypesFor(MethodBinding method) {
	ReferenceBinding[] exceptions = method.thrownExceptions;
	if ((method.modifiers & ExtraCompilerModifiers.AccUnresolved) == 0)
		return exceptions;

	if (!(method.declaringClass instanceof BinaryTypeBinding))
		return Binding.NO_EXCEPTIONS; // safety check

	for (int i = exceptions.length; --i >= 0;)
		exceptions[i] = (ReferenceBinding) BinaryTypeBinding.resolveType(exceptions[i], this.environment, true /* raw conversion */);
	return exceptions;
}
