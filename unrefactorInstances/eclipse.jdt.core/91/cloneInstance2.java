public char[] remove(char[] object) {
	int length = this.values.length;
	int index = (CharOperation.hashCode(object) & 0x7FFFFFFF) % length;
	char[] current;
	while ((current = this.values[index]) != null) {
		if (CharOperation.equals(current, object)) {
			this.elementSize--;
			char[] oldValue = this.values[index];
			this.values[index] = null;
			if (this.values[index + 1 == length ? 0 : index + 1] != null)
				rehash(); // only needed if a possible collision existed
			return oldValue;
		}
		if (++index == length) index = 0;
	}
	return null;
}
