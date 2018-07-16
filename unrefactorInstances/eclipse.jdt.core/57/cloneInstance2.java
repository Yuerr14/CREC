		while (tokenizer.hasMoreTokens()) {
			f = new File(outDir.append(token).append(fileSeparator).toString());
			if (f.exists()) {
				// The outDir already exists, so we proceed the next entry
				// System.out.println("outDir: " + outDir + " already exists.");
			} else {
				// Need to add the outDir
				if (!f.mkdir()) {
					System.out.println(Util.bind("output.fileName" , f.getName())); //$NON-NLS-1$
					throw new IOException(Util.bind("output.notValid" )); //$NON-NLS-1$
				}
			}
			token = tokenizer.nextToken();
		}
