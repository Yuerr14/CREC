	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Object[] element;
		for (int i = 0, length = this.keyTable.length; i < length; i++)
			if ((element = this.keyTable[i]) != null) {
				buffer.append('{');
				for (int j = 0, length2 = element.length; j < length2; j++) {
					buffer.append(element[j]);
					if (j != length2-1)
						buffer.append(", "); //$NON-NLS-1$
				}
				buffer.append("} -> ");  //$NON-NLS-1$
				buffer.append(this.valueTable[i]);
				if (i != length-1)
					buffer.append('\n');
			}
		return buffer.toString();
	}
