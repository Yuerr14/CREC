					if ((anInterface.tagBits & HasNoMemberTypes) == 0) { // skip interface if it already knows it has no member types
						if (anInterface.hasMemberTypes()) // avoid resolving member types eagerly
							return;

						needToTag = true;
						ReferenceBinding[] itsInterfaces = anInterface.superInterfaces();
						if (itsInterfaces != NoSuperInterfaces) {
							if (++lastPosition == interfacesToVisit.length)
								System.arraycopy(interfacesToVisit, 0, interfacesToVisit = new ReferenceBinding[lastPosition * 2][], 0, lastPosition);
							interfacesToVisit[lastPosition] = itsInterfaces;
						}
					}
