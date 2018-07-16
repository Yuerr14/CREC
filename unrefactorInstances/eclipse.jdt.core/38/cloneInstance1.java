	public int flushCommentsDefinedPriorTo(int position) {

		int lastCommentIndex = this.scanner.commentPtr;
		if (lastCommentIndex < 0) return position; // no comment
	
		// compute the index of the first obsolete comment
		int index = lastCommentIndex;
		int validCount = 0;
		while (index >= 0){
			int commentEnd = this.scanner.commentStops[index];
			if (commentEnd < 0) commentEnd = -commentEnd; // negative end position for non-javadoc comments
			if (commentEnd <= position){
				break;
			}
			index--;
			validCount++;
		}
		// if the source at <position> is immediately followed by a line comment, then
		// flush this comment and shift <position> to the comment end.
		if (validCount > 0){
			int immediateCommentEnd = 0;
			while (index<lastCommentIndex && (immediateCommentEnd = -this.scanner.commentStops[index+1])  > 0){ // only tolerating non-javadoc comments (non-javadoc comment end positions are negative)
				// is there any line break until the end of the immediate comment ? (thus only tolerating line comment)
				immediateCommentEnd--; // comment end in one char too far
				if (this.scanner.getLineNumber(position) != this.scanner.getLineNumber(immediateCommentEnd)) break;
				position = immediateCommentEnd;
				validCount--; // flush this comment
				index++;
			}
		}
	
		if (index < 0) return position; // no obsolete comment
		pushOnCommentsStack(0, index); // store comment before flushing them

		switch (validCount) {
			case 0:
				// do nothing
				break;
			// move valid comment infos, overriding obsolete comment infos
			case 2:
				this.scanner.commentStarts[0] = this.scanner.commentStarts[index+1];
				this.scanner.commentStops[0] = this.scanner.commentStops[index+1];
				this.scanner.commentTagStarts[0] = this.scanner.commentTagStarts[index+1];
				this.scanner.commentStarts[1] = this.scanner.commentStarts[index+2];
				this.scanner.commentStops[1] = this.scanner.commentStops[index+2];
				this.scanner.commentTagStarts[1] = this.scanner.commentTagStarts[index+2];
				break;
			case 1:
				this.scanner.commentStarts[0] = this.scanner.commentStarts[index+1];
				this.scanner.commentStops[0] = this.scanner.commentStops[index+1];
				this.scanner.commentTagStarts[0] = this.scanner.commentTagStarts[index+1];
				break;
			default:
				System.arraycopy(this.scanner.commentStarts, index + 1, this.scanner.commentStarts, 0, validCount);
				System.arraycopy(this.scanner.commentStops, index + 1, this.scanner.commentStops, 0, validCount);		
				System.arraycopy(this.scanner.commentTagStarts, index + 1, this.scanner.commentTagStarts, 0, validCount);
		}
		this.scanner.commentPtr = validCount - 1;
		return position;
	}
