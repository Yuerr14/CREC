	public SourceRange getSourceRange(IJavaElement element) {
		switch(element.getElementType()) {
			case IJavaElement.METHOD :
				if (((IMember) element).isBinary()) {
					IJavaElement[] el = getUnqualifiedMethodHandle((IMethod) element, false);
					if(el[1] != null && this.sourceRanges.get(el[0]) == null) {
						element = getUnqualifiedMethodHandle((IMethod) element, true)[0];
					} else {
						element = el[0];
					}
				}
				break;
			case IJavaElement.TYPE_PARAMETER :
				IJavaElement parent = element.getParent();
				if (parent.getElementType() == IJavaElement.METHOD) {
					IMethod method = (IMethod) parent;
					if (method.isBinary()) {
						IJavaElement[] el = getUnqualifiedMethodHandle(method, false);
						if(el[1] != null && this.sourceRanges.get(el[0]) == null) {
							method = (IMethod) getUnqualifiedMethodHandle(method, true)[0];
						} else {
							method = (IMethod) el[0];
						}
						element = method.getTypeParameter(element.getElementName());
					}
				}
				break;
			case IJavaElement.LOCAL_VARIABLE :
				LocalVariableElementKey key = new LocalVariableElementKey(element.getParent(), element.getElementName());
				SourceRange[] ranges = (SourceRange[]) this.parametersRanges.get(key);
				if (ranges == null) {
					return UNKNOWN_RANGE;
				} else {
					return ranges[0];
				}
		}
		SourceRange[] ranges = (SourceRange[]) this.sourceRanges.get(element);
		if (ranges == null) {
			return UNKNOWN_RANGE;
		} else {
			return ranges[0];
		}
	}
