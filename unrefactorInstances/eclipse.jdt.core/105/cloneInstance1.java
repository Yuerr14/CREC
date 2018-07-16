	} else {
		IBinaryMethod methodInfo = (IBinaryMethod) info;
		int flags = methodInfo.getModifiers();
		if (Flags.isStatic(flags)) {
			buffer.append("static "); //$NON-NLS-1$
		}
		if (!methodInfo.isConstructor()) {
			buffer.append(Signature.toString(getReturnType(methodInfo)));
			buffer.append(' ');
		}
		toStringName(buffer, flags);
	}
