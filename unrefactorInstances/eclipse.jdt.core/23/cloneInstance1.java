	public static IStatus validateCompilationUnitName(String name) {
		if (name == null) {
			return new Status(IStatus.ERROR, JavaCore.PLUGIN_ID, -1, Util.bind("convention.unit.nullName"), null); //$NON-NLS-1$
		}
		if (!Util.isJavaFileName(name)) {
			return new Status(IStatus.ERROR, JavaCore.PLUGIN_ID, -1, Util.bind("convention.unit.notJavaName"), null); //$NON-NLS-1$
		}
		String identifier;
		int index;
		index = name.lastIndexOf('.');
		if (index == -1) {
			return new Status(IStatus.ERROR, JavaCore.PLUGIN_ID, -1, Util.bind("convention.unit.notJavaName"), null); //$NON-NLS-1$
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
