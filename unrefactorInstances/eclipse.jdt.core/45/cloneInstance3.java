		for (int j = 0, length = interfaces.length; j < length; j++) {
			if ((currentType = interfaces[j]).isEquivalentTo(anInterface))
				return true;

			ReferenceBinding[] itsInterfaces = currentType.superInterfaces();
			if (itsInterfaces != NoSuperInterfaces) {
				if (++lastPosition == interfacesToVisit.length)
					System.arraycopy(interfacesToVisit, 0, interfacesToVisit = new ReferenceBinding[lastPosition * 2][], 0, lastPosition);
				interfacesToVisit[lastPosition] = itsInterfaces;
			}
		}
