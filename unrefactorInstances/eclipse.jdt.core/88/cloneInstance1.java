		if (isMatchingIndexRecord()) {
			int[] references = entry.getFileReferences();
			for (int iReference = 0, refererencesLength = references.length; iReference < refererencesLength; iReference++) {
				String documentPath = IndexedFile.convertPath( input.getIndexedFile(references[iReference]).getPath());
				if (scope.encloses(documentPath)) {
					if (!requestor.acceptIndexMatch(documentPath, record, participant)) 
						throw new OperationCanceledException();
				}
			}
			}
