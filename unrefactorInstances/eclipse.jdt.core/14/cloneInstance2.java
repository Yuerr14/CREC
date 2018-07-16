	private void addDevEntries(String devBase, List result) throws MalformedURLException {
		String[] locations = getArrayFromList(devClassPath);
		for (int i = 0; i < locations.length; i++) {
			String spec = devBase + locations[i];
			char lastChar = spec.charAt(spec.length() - 1);
			URL url;
			if ((spec.endsWith(".jar") || (lastChar == '/' || lastChar == '\\'))) //$NON-NLS-1$
				url = new URL(spec);
			else
				url = new URL(spec + "/"); //$NON-NLS-1$
			addEntry(url, result);
		}
	}
