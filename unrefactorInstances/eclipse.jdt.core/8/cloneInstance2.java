	} else if (currentNumberOfLocals == 0) {
		// need to check if there is a hole in the prev locals
		int counter = 0;
		result = -prevNumberOfLocals; // chop frame if no hole and prevNumberOfLocals <= 3
		for(int i = 0; i < prevLocalsLength && counter < prevNumberOfLocals; i++) {
			if (prevLocals[i] != null) {
				switch(prevLocals[i].id()) {
					case TypeIds.T_double :
					case TypeIds.T_long :
						i++;
				}
				counter++;
			} else {
				result = Integer.MAX_VALUE;
				this.numberOfDifferentLocals = result;
				return result;
			}
		}
	} else {
