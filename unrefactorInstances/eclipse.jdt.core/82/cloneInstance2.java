	public void addSyntheticFieldReadAccessMethod(SyntheticAccessMethodBinding methodBinding) {
		generateMethodInfoHeader(methodBinding);
		// We know that we won't get more than 2 attribute: the code attribute + synthetic attribute
		contents[contentsOffset++] = 0;
		contents[contentsOffset++] = 2;
		// Code attribute
		int codeAttributeOffset = contentsOffset;
		generateCodeAttributeHeader();
		codeStream.init(this);
		codeStream.generateSyntheticBodyForFieldReadAccess(methodBinding);
		completeCodeAttributeForSyntheticAccessMethod(
			methodBinding,
			codeAttributeOffset,
			((SourceTypeBinding) methodBinding.declaringClass)
				.scope
				.referenceCompilationUnit()
				.compilationResult
				.lineSeparatorPositions);
		// add the synthetic attribute
		int syntheticAttributeNameIndex =
			constantPool.literalIndex(AttributeNamesConstants.SyntheticName);
		contents[contentsOffset++] = (byte) (syntheticAttributeNameIndex >> 8);
		contents[contentsOffset++] = (byte) syntheticAttributeNameIndex;
		// the length of a synthetic attribute is equals to 0
		contents[contentsOffset++] = 0;
		contents[contentsOffset++] = 0;
		contents[contentsOffset++] = 0;
		contents[contentsOffset++] = 0;
	}
