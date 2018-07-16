	private int generateSyntheticAttribute() {
		int localContentsOffset = this.contentsOffset;
		if (localContentsOffset + 6 >= this.contents.length) {
			resizeContents(6);
		}
		int syntheticAttributeNameIndex =
			this.constantPool.literalIndex(AttributeNamesConstants.SyntheticName);
		this.contents[localContentsOffset++] = (byte) (syntheticAttributeNameIndex >> 8);
		this.contents[localContentsOffset++] = (byte) syntheticAttributeNameIndex;
		// the length of a synthetic attribute is equals to 0
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contentsOffset = localContentsOffset;
		return 1;
	}
