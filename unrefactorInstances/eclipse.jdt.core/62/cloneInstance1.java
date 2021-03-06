	private int generateDeprecatedAttribute() {
		int localContentsOffset = this.contentsOffset;
		if (localContentsOffset + 6 >= this.contents.length) {
			resizeContents(6);
		}
		int deprecatedAttributeNameIndex =
			this.constantPool.literalIndex(AttributeNamesConstants.DeprecatedName);
		this.contents[localContentsOffset++] = (byte) (deprecatedAttributeNameIndex >> 8);
		this.contents[localContentsOffset++] = (byte) deprecatedAttributeNameIndex;
		// the length of a deprecated attribute is equals to 0
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contentsOffset = localContentsOffset;
		return 1;
	}
