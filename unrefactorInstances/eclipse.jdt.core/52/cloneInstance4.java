	public StringBuffer print(int indent, StringBuffer output) {

		printIndent(indent, output);
		output.append("<SelectionOnArgumentName:"); //$NON-NLS-1$
		if (this.type != null) this.type.print(0, output).append(' ');
		output.append(this.name);
		if (this.initialization != null) {
			output.append(" = ");//$NON-NLS-1$
			this.initialization.printExpression(0, output);
		}
		return output.append('>');
	}
