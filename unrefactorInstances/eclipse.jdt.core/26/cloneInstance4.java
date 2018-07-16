	if (typeBinding == DoubleBinding) {
		switch (resolvedPosition) {
			case 0 :
				this.dload_0();
				break;
			case 1 :
				this.dload_1();
				break;
			case 2 :
				this.dload_2();
				break;
			case 3 :
				this.dload_3();
				break;
			default :
				this.dload(resolvedPosition);
		}
		return;
	}
