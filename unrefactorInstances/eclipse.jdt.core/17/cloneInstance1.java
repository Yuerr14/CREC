								if (!firstLevelPackageNames.contains(firstLevelPackageName)) {
									if (sourceLevel == null) {
										IJavaProject project = root.getJavaProject();
										sourceLevel = project.getOption(JavaCore.COMPILER_SOURCE, true);
										complianceLevel = project.getOption(JavaCore.COMPILER_COMPLIANCE, true);
									}
									IStatus status = JavaConventions.validatePackageName(firstLevelPackageName, sourceLevel, complianceLevel);
									if (status.isOK() || status.getSeverity() == IStatus.WARNING) {
										firstLevelPackageNames.add(firstLevelPackageName);
									}
								}
