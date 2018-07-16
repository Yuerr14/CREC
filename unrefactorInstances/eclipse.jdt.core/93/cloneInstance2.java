	while (true) {
		longs[longIndex++] = 
		  (((long) (bytes[i++] & 0xFF)) << 56)
		+ (((long) (bytes[i++] & 0xFF)) << 48)
		+ (((long) (bytes[i++] & 0xFF)) << 40)
		+ (((long) (bytes[i++] & 0xFF)) << 32)
		+ (((long) (bytes[i++] & 0xFF)) << 24)
		+ (((long) (bytes[i++] & 0xFF)) << 16)
		+ (((long) (bytes[i++] & 0xFF)) << 8)
		+ (bytes[i++] & 0xFF);
		
		if (i == length)
			break;
	}
