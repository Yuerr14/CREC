	{	
		if (i2Load.getValue().endsWith(".jar")) {
			File jarFile = ruby.findFile(new File(i2Load.getValue()));
			if (!jarFile.exists()) {
				ruby.getRuntime().getErrorStream().println("[Error] Jarfile + \"" + jarFile.getAbsolutePath() + "\"not found.");
			}
			else {
				/*try {
					ClassLoader javaClassLoader = new URLClassLoader(new URL[] { jarFile.toURL()}, ruby.getJavaClassLoader());
					ruby.setJavaClassLoader(javaClassLoader);
				} catch (MalformedURLException murlExcptn) {
				}*/
			}
		} else {
			if (!i2Load.getValue().endsWith(".rb")) {
				i2Load = RubyString.newString(ruby, i2Load.getValue() + ".rb");
			}
			File rbFile = ruby.findFile(new File(i2Load.getValue()));
			ruby.getRuntime().loadFile(rbFile, false);
		}
		return ruby.getTrue();
	}
