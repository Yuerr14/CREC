	if (prevNumberOfLocals == 0) {
		if (currentNumberOfLocals != 0) {
			// need to check if there is a hole in the locals
			result = currentNumberOfLocals; // append if no hole and currentNumberOfLocals <= 3
			int counter = 0;
			for(int i = 0; i < currentLocalsLength && counter < currentNumberOfLocals; i++) {
				if (currentLocals[i] != null) {
					switch(currentLocals[i].id()) {
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
		}
	} else if (currentNumberOfLocals == 0) {
