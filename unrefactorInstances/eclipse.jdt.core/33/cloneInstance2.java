boolean isAsVisible(MethodBinding newMethod, MethodBinding inheritedMethod) {
	if (inheritedMethod.modifiers == newMethod.modifiers) return true;

	if (newMethod.isPublic()) return true;		// Covers everything
	if (inheritedMethod.isPublic()) return false;

	if (newMethod.isProtected()) return true;
	if (inheritedMethod.isProtected()) return false;

	return !newMethod.isPrivate();		// The inheritedMethod cannot be private since it would not be visible
}
