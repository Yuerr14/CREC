		while (length != 0) {
			int x = reference[readOffset++] & 0xFF;
			length--;
			if ((0x80 & x) != 0) {
				if ((x & 0x20) != 0) {
					length-=2;
					x = ((x & 0xF) << 12) + ((reference[readOffset++] & 0x3F) << 6) + (reference[readOffset++] & 0x3F);
				} else {
					length--;
					x = ((x & 0x1F) << 6) + (reference[readOffset++] & 0x3F);
				}
			}
			outputBuf[outputPos++] = (char) x;
		}
