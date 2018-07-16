public ReferenceBinding superclass() {
	if ((this.tagBits & HasUnresolvedSuperclass) == 0)
		return this.superclass;

	this.superclass = resolveUnresolvedType(this.superclass, this.environment, true);
	this.tagBits ^= HasUnresolvedSuperclass;

	// finish resolving the type
	this.superclass = resolveType(this.superclass, this.environment, true);
	return this.superclass;
}
