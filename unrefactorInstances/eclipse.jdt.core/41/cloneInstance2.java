public CompilationUnitDeclaration updatedCompilationUnitDeclaration(){

	/* update imports */
	if (this.importCount > 0){
		ImportReference[] importRefences = new ImportReference[this.importCount];
		for (int i = 0; i < this.importCount; i++){
			importRefences[i] = this.imports[i].updatedImportReference();
		}
		this.unitDeclaration.imports = importRefences;
	}
	/* update types */
	if (this.typeCount > 0){
		int existingCount = this.unitDeclaration.types == null ? 0 : this.unitDeclaration.types.length;
		TypeDeclaration[] typeDeclarations = new TypeDeclaration[existingCount + this.typeCount];
		if (existingCount > 0){
			System.arraycopy(this.unitDeclaration.types, 0, typeDeclarations, 0, existingCount);
		}
		// may need to update the declarationSourceEnd of the last type
		if (this.types[this.typeCount - 1].typeDeclaration.declarationSourceEnd == 0){
			this.types[this.typeCount - 1].typeDeclaration.declarationSourceEnd = this.unitDeclaration.sourceEnd;
			this.types[this.typeCount - 1].typeDeclaration.bodyEnd = this.unitDeclaration.sourceEnd;
		}
		int actualCount = existingCount;
		for (int i = 0; i < this.typeCount; i++){
			TypeDeclaration typeDecl = this.types[i].updatedTypeDeclaration();
			// filter out local types (12454)
			if ((typeDecl.bits & ASTNode.IsLocalTypeMASK) == 0){
				typeDeclarations[actualCount++] = typeDecl;
			}
		}
		if (actualCount != this.typeCount){
			System.arraycopy(
				typeDeclarations, 
				0, 
				typeDeclarations = new TypeDeclaration[existingCount+actualCount], 
				0, 
				existingCount+actualCount);
		}
		this.unitDeclaration.types = typeDeclarations;
	}
	return this.unitDeclaration;
}
