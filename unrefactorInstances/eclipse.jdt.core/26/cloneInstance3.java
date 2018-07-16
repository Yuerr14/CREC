	if (typeBinding == LongBinding) {
		switch (resolvedPosition) {
			case 0 :
				this.lload_0();
				break;
			case 1 :
				this.lload_1();
				break;
			case 2 :
				this.lload_2();
				break;
			case 3 :
				this.lload_3();
				break;
			default :
				this.lload(resolvedPosition);
		}
		return;
	}
