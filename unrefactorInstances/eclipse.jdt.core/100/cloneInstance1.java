		} else if (getNextChar('b', 'B') >= 0) { //----------binary-----------------
			int start = this.currentPosition;
			consumeDigits(2, true);
			int end = this.currentPosition;
			if (end == start) {
				if (this.sourceLevel < ClassFileConstants.JDK1_7) {
					throw new InvalidInputException(BINARY_LITERAL_NOT_BELOW_17);
				}
				throw new InvalidInputException(INVALID_BINARY);
			}
			if (getNextChar('l', 'L') >= 0) {
				if (this.sourceLevel < ClassFileConstants.JDK1_7) {
					throw new InvalidInputException(BINARY_LITERAL_NOT_BELOW_17);
				}
				return TokenNameLongLiteral;
			}
			if (this.sourceLevel < ClassFileConstants.JDK1_7) {
				throw new InvalidInputException(BINARY_LITERAL_NOT_BELOW_17);
			}
			return TokenNameIntegerLiteral;
		}
