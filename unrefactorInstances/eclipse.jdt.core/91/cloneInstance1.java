public Object remove(Object object) {
	int length = this.values.length;
	int index = (object.hashCode() & 0x7FFFFFFF) % length;
	Object current;
	while ((current = this.values[index]) != null) {
		if (current.equals(object)) {
			this.elementSize--;
			Object oldValue = this.values[index];
			this.values[index] = null;
			if (this.values[index + 1 == length ? 0 : index + 1] != null)
				rehash(); // only needed if a possible collision existed
			return oldValue;
		}
		if (++index == length) index = 0;
	}
	return null;
}
