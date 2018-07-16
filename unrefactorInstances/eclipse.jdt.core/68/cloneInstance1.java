	public void setLeadingComment(String comment) {
		if (comment != null) {
			char[] source = comment.toCharArray();
			Scanner scanner = this.ast.scanner;
			scanner.resetTo(0, source.length);
			scanner.setSource(source);
			try {
				int token;
				boolean onlyOneComment = false;
				while ((token = scanner.getNextToken()) != TerminalTokens.TokenNameEOF) {
					switch(token) {
						case TerminalTokens.TokenNameCOMMENT_BLOCK :
						case TerminalTokens.TokenNameCOMMENT_JAVADOC :
						case TerminalTokens.TokenNameCOMMENT_LINE :
							if (onlyOneComment) {
								throw new IllegalArgumentException();
							}
							onlyOneComment = true;
							break;
						default:
							onlyOneComment = false;
					}
				}
				if (!onlyOneComment) {
					throw new IllegalArgumentException();
				}
			} catch (InvalidInputException e) {
				throw new IllegalArgumentException();
			}
		}
		// we do not consider the obsolete comment as a structureal property
		// but we protect them nevertheless
		checkModifiable();
		this.optionalLeadingComment = comment;
	}
