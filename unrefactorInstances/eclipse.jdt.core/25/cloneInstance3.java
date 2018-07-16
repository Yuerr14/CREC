		if (bindings.length == 1 && bindings[0] instanceof ITypeBinding) {
			ITypeBinding typeBinding= (ITypeBinding) bindings[0];
			IAnnotationBinding[] annotations = typeBinding.getAnnotations();
			for (int i = 0, max = annotations.length; i < max; i++) {
				IAnnotationBinding annotation = annotations[i];
				IMemberValuePairBinding[] allMemberValuePairs = annotation.getAllMemberValuePairs();
				for (int j = 0, max2 = allMemberValuePairs.length; j < max2; j++) {
					IMemberValuePairBinding memberValuePair = allMemberValuePairs[j];
					Object defaultValue = memberValuePair.getValue();
					System.out.println(defaultValue);
					assertNotNull("no default value", defaultValue);
				}
			}
		}
