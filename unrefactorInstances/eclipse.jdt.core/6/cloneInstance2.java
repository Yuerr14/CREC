	while (length != 0) {
		int x = this.reference[readOffset++] & 0xFF;
		length--;
		if ((0x80 & x) != 0) {
			if ((x & 0x20) != 0) {
				length-=2;
				x = ((x & 0xF) << 12) | ((this.reference[readOffset++] & 0x3F) << 6) | (this.reference[readOffset++] & 0x3F);
			} else {
				length--;
				x = ((x & 0x1F) << 6) | (this.reference[readOffset++] & 0x3F);
			}
		}
		outputBuf[outputPos++] = (char) x;
	}
