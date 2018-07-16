	public StringBuffer printAsExpression(int indent, StringBuffer output) {
		printIndent(indent, output);
		output.append("<SelectionOnLocalName:"); //$NON-NLS-1$
		printModifiers(this.modifiers, output);
		 this.type.print(0, output).append(' ').append(this.name);
		if (this.initialization != null) {
			output.append(" = "); //$NON-NLS-1$
			this.initialization.printExpression(0, output);
		}
		return output.append('>');
	}
