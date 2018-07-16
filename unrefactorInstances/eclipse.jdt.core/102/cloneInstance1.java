protected void markEnclosingMemberWithLocalType() {
	if (this.currentElement != null) return; // this is already done in the recovery code
	for (int i = this.astPtr; i >= 0; i--) {
		ASTNode node = this.astStack[i];
		if (node instanceof AbstractMethodDeclaration
				|| node instanceof FieldDeclaration
				|| (node instanceof TypeDeclaration // mark type for now: all initializers will be marked when added to this type
						// and enclosing type must not be closed (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=147485)
						&& ((TypeDeclaration) node).declarationSourceEnd == 0)) {
			node.bits |= ASTNode.HasLocalType;
			return;
		}
	}
	// default to reference context (case of parse method body)
	if (this.referenceContext instanceof AbstractMethodDeclaration
			|| this.referenceContext instanceof TypeDeclaration) {
		((ASTNode)this.referenceContext).bits |= ASTNode.HasLocalType;
	}
}
