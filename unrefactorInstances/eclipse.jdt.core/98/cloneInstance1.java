			for (int j = 0, k = currentArgs.length; j < k; j++) {
				TypeBinding currentArg = currentArgs[j].leafComponentType();
				TypeBinding inheritedArg = inheritedArgs[j].leafComponentType();
				if (currentArg != inheritedArg) {
					if (currentArg.isParameterizedType() && hasBoundedParameters((ParameterizedTypeBinding) currentArg)) {
						if (inheritedArg.isRawType()) {
//						if (inheritedArg.isRawType() || !inheritedArg.isEquivalentTo(currentArg)) {
							this.problemReporter(currentMethod).methodNameClash(currentMethod, inheritedMethod);
							continue nextMethod;
						}
					}
				}
			}
