    public String[] getAllNamesInScope() {
        String[] names = getVariables();
        if (isBlockOrEval) {
            String[] ourVariables = names;
            String[] variables = enclosingScope.getAllNamesInScope();

            // we know variables cannot be null since this IRStaticScope always returns a non-null array
            names = new String[variables.length + ourVariables.length];

            System.arraycopy(variables, 0, names, 0, variables.length);
            System.arraycopy(ourVariables, 0, names, variables.length, ourVariables.length);
        }

        return names;
    }
