		try {
			if (location == null)
				result = defaultLocation;
			else if (location.equalsIgnoreCase(NONE))
				return null;
			else if (location.equalsIgnoreCase(NO_DEFAULT))
				result = buildURL(location, true);
			else {
				if (location.equalsIgnoreCase(USER_HOME))
					location = computeDefaultUserAreaLocation(userDefaultAppendage);
				if (location.equalsIgnoreCase(USER_DIR))
					location = new File(System.getProperty(PROP_USER_DIR), userDefaultAppendage).getAbsolutePath();
				result = buildURL(location, true);
			}
		} finally {
