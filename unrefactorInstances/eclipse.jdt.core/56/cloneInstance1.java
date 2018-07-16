	public static boolean equalArrays(Object[] a, Object[] b, int len) {
		if (a == b)	return true;
		if (a.length < len || b.length < len) return false;
		for (int i = 0; i < len; ++i) {
			if (a[i] == null) {
				if (b[i] != null) return false;
			} else {
				if (!a[i].equals(b[i])) return false;
			}
		}
		return true;
	}
