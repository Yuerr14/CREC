	public static IStatus validateClassFileName(String name) {
		if (name == null) {
			return new Status(IStatus.ERROR, JavaCore.PLUGIN_ID, -1, Util.bind("convention.classFile.nullName"), null); //$NON-NLS-1$
		}
		if (!Util.isClassFileName(name)) {
			return new Status(IStatus.ERROR, JavaCore.PLUGIN_ID, -1, Util.bind("convention.classFile.notClassFileName"), null); //$NON-NLS-1$
		}
		String identifier;
		int index;
		index = name.lastIndexOf('.');
		if (index == -1) {
			return new Status(IStatus.ERROR, JavaCore.PLUGIN_ID, -1, Util.bind("convention.classFile.notClassFileName"), null); //$NON-NLS-1$
		}
		identifier = name.substring(0, index);
		IStatus status = validateIdentifier(identifier);
		if (!status.isOK()) {
			return status;
		}
		status = ResourcesPlugin.getWorkspace().validateName(name, IResource.FILE);
		if (!status.isOK()) {
			return status;
		}
		return JavaModelStatus.VERIFIED_OK;
	}
