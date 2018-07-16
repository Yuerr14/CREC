	if (thrownExceptions != null) {
		if (thrownExceptions != NoExceptions) {
			s += "throws "; //$NON-NLS-1$
			for (int i = 0, length = thrownExceptions.length; i < length; i++) {
				if (i  > 0)
					s += ", "; //$NON-NLS-1$
				s += (thrownExceptions[i] != null) ? thrownExceptions[i].debugName() : "NULL TYPE"; //$NON-NLS-1$
			}
		}
	} else {
