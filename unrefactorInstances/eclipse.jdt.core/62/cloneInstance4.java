	private int generateVarargsAttribute() {
		int localContentsOffset = this.contentsOffset;
		/*
		 * handle of the target jsr14 for varargs in the source
		 * Varargs attribute
		 * Check that there is enough space to write the attribute
		 */
		if (localContentsOffset + 6 >= this.contents.length) {
			resizeContents(6);
		}
		int varargsAttributeNameIndex =
			this.constantPool.literalIndex(AttributeNamesConstants.VarargsName);
		this.contents[localContentsOffset++] = (byte) (varargsAttributeNameIndex >> 8);
		this.contents[localContentsOffset++] = (byte) varargsAttributeNameIndex;
		// the length of a varargs attribute is equals to 0
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;

		this.contentsOffset = localContentsOffset;
		return 1;
	}
