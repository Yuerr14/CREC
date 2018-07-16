	private boolean verifySpaceOrEndComment() {
		int startPosition = this.index;
		// Whitespace or inline tag closing brace
		char ch = peekChar();
		switch (ch) {
			case '}':
				return this.inlineTagStarted;
			default:
				if (Character.isWhitespace(ch)) {
					return true;
				}
		}
		// End of comment
		int previousPosition = this.index;
		this.starPosition = -1;
		ch = readChar();
		nextChar: while (this.index<this.source.length) {
			switch (ch) {
				case '*':
					// valid whatever the number of star before last '/'
					this.starPosition = previousPosition;
					break;
				case '/':
					if (this.starPosition >= startPosition) { // valid only if a star was previous character
						return true;
					}
				default :
					// invalid whatever other character, even white spaces
					this.index = startPosition;
					return false;
				
			}
			previousPosition = this.index;
			ch = readChar();
		}
		this.index = startPosition;
		return false;
	}
