		nextChar: while (true) {
			switch (ch) {
				case '\r':
				case '\n':
					if (this.kind == DOM_PARSER) {
						createTag();
						pushText(textPosition, previousPosition);
					}
					this.index = previousPosition;
					return true;
				case '\u000c' :	/* FORM FEED               */
				case ' ' :			/* SPACE                   */
				case '\t' :			/* HORIZONTAL TABULATION   */
					if (this.starPosition >= 0) break nextChar;
					break;
				case '*':
					this.starPosition = previousPosition;
					break;
				case '/':
					if (this.starPosition >= textPosition) {
						if (this.kind == DOM_PARSER) {
							createTag();
							pushText(textPosition, this.starPosition);
						}
						return true;
					}
				default :
					// leave loop
					break nextChar;
				
			}
			previousPosition = this.index;
			ch = readChar();
		}
