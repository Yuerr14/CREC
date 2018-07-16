							if (member.getType() == IResource.FOLDER) {
								if (sourceLevel == null) {
									IJavaProject project = root.getJavaProject();
									sourceLevel = project.getOption(JavaCore.COMPILER_SOURCE, true);
									complianceLevel = project.getOption(JavaCore.COMPILER_COMPLIANCE, true);
								}
								IStatus status = JavaConventions.validatePackageName(resourceName, sourceLevel, complianceLevel);
								if (status.isOK() || status.getSeverity() == IStatus.WARNING) {
									firstLevelPackageNames.add(resourceName);
								}
							} else if (Util.isClassFileName(resourceName)) {
