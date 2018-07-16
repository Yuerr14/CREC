			for (int j = 0, length = interfaces.length; j < length; j++) {
				currentType = interfaces[j];
				if (currentType.erasure() == newErasure)
					if (currentType != newInterface)
						return currentType;

				ReferenceBinding[] itsInterfaces = currentType.superInterfaces();
				if (itsInterfaces != NoSuperInterfaces) {
					if (++lastPosition == interfacesToVisit.length)
						System.arraycopy(interfacesToVisit, 0, interfacesToVisit = new ReferenceBinding[lastPosition * 2][], 0, lastPosition);
					interfacesToVisit[lastPosition] = itsInterfaces;
				}
			}
