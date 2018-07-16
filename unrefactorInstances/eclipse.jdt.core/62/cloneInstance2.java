	private int generateHierarchyInconsistentAttribute() {
		int localContentsOffset = this.contentsOffset;
		// add an attribute for inconsistent hierarchy
		if (localContentsOffset + 6 >= this.contents.length) {
			resizeContents(6);
		}
		int inconsistentHierarchyNameIndex =
			this.constantPool.literalIndex(AttributeNamesConstants.InconsistentHierarchy);
		this.contents[localContentsOffset++] = (byte) (inconsistentHierarchyNameIndex >> 8);
		this.contents[localContentsOffset++] = (byte) inconsistentHierarchyNameIndex;
		// the length of an inconsistent hierarchy attribute is equals to 0
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contents[localContentsOffset++] = 0;
		this.contentsOffset = localContentsOffset;
		return 1;
	}
