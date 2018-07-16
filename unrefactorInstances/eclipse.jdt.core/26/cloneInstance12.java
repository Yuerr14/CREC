public final void loadObject(int resolvedPosition) {
	switch (resolvedPosition) {
		case 0 :
			this.aload_0();
			break;
		case 1 :
			this.aload_1();
			break;
		case 2 :
			this.aload_2();
			break;
		case 3 :
			this.aload_3();
			break;
		default :
			this.aload(resolvedPosition);
	}
}
