		} catch (OutOfMemoryError oom) {
			// DEBUG
			oom.printStackTrace();
			System.err.println("-------------------- DEBUG --------------------"); //$NON-NLS-1$
			System.err.println("file = "+this.indexLocation); //$NON-NLS-1$
			System.err.println("offset = "+offset); //$NON-NLS-1$
			System.err.println("size = "+size); //$NON-NLS-1$
			System.err.println("--------------------   END   --------------------"); //$NON-NLS-1$
			throw oom;
		}
