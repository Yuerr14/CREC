	if (typeBinding == FloatBinding) {
		switch (resolvedPosition) {
			case 0 :
				this.fload_0();
				break;
			case 1 :
				this.fload_1();
				break;
			case 2 :
				this.fload_2();
				break;
			case 3 :
				this.fload_3();
				break;
			default :
				this.fload(resolvedPosition);
		}
		return;
	}
