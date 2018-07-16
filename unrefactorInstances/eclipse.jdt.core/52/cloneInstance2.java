	public StringBuffer printAsExpression(int indent, StringBuffer output) {
		printIndent(indent, output);
		output.append("<CompleteOnLocalName:"); //$NON-NLS-1$
		if (this.type != null)  this.type.print(0, output).append(' ');
		output.append(this.realName);
		if (this.initialization != null) {
			output.append(" = "); //$NON-NLS-1$
			this.initialization.printExpression(0, output);
		}
		return output.append('>');
	}
