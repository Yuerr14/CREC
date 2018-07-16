	    if (variableSubstitutes != null) {
	        int length = variableSubstitutes.length;
	        for (int i = 0; i < length; i++) {
	            if (variableSubstitutes[i] == otherType) return; // already there
	            if (variableSubstitutes[i] == null) {
	                variableSubstitutes[i] = otherType;
	                return;
	            }
	        }
	        // no free spot found, need to grow
	        System.arraycopy(variableSubstitutes, 0, variableSubstitutes = new TypeBinding[2*length], 0, length);
	        variableSubstitutes[length] = otherType;
	        substitutes.put(this, variableSubstitutes);
	    }
