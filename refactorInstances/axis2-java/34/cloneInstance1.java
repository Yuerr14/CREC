            if (libfiles.exists()) {
                urls.add(libfiles.toURL());
                File jarfiles[] = libfiles.listFiles();
                for (int i = 0; i < jarfiles.length; i++) {
                    File jarfile = jarfiles[i];
                    if (jarfile.getName().endsWith(".jar")) {
                        urls.add(jarfile.toURL());
                    }
                }
            } else {
