	} else {
		SourceMethodElementInfo methodInfo = (SourceMethodElementInfo) info;
		int flags = methodInfo.getModifiers();
		if (Flags.isStatic(flags)) {
			buffer.append("static "); //$NON-NLS-1$
		}
		if (!methodInfo.isConstructor()) {
			buffer.append(methodInfo.getReturnTypeName());
			buffer.append(' ');
		}
		toStringName(buffer, flags);
	}
