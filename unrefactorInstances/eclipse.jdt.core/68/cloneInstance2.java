	public void setComment(String docComment) {
	    supportedOnlyIn2();
		if (docComment == null) {
			throw new IllegalArgumentException();
		}
		char[] source = docComment.toCharArray();
		Scanner scanner = this.ast.scanner;
		scanner.resetTo(0, source.length);
		scanner.setSource(source);
		try {
			int token;
			boolean onlyOneComment = false;
			while ((token = scanner.getNextToken()) != TerminalTokens.TokenNameEOF) {
				switch(token) {
					case TerminalTokens.TokenNameCOMMENT_JAVADOC :
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
		preValueChange(COMMENT_PROPERTY);
		this.comment = docComment;
		postValueChange(COMMENT_PROPERTY);
	}
