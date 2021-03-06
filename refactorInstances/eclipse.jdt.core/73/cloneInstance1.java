		if (this.referenceBinding.isDeprecated()) {
			// check that there is enough space to write all the bytes for the field info corresponding
			// to the @fieldBinding
			if (this.contentsOffset + 6 >= this.contents.length) {
				resizeContents(6);
			}
			int deprecatedAttributeNameIndex =
				this.constantPool.literalIndex(AttributeNamesConstants.DeprecatedName);
			this.contents[this.contentsOffset++] = (byte) (deprecatedAttributeNameIndex >> 8);
			this.contents[this.contentsOffset++] = (byte) deprecatedAttributeNameIndex;
			// the length of a deprecated attribute is equals to 0
			this.contents[this.contentsOffset++] = 0;
			this.contents[this.contentsOffset++] = 0;
			this.contents[this.contentsOffset++] = 0;
			this.contents[this.contentsOffset++] = 0;
			attributesNumber++;
		}
