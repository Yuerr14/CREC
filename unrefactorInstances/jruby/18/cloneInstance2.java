    public void compileGlobalVar(Node node, BodyCompiler context) {
        GlobalVarNode globalVarNode = (GlobalVarNode) node;

        if (globalVarNode.getName().length() == 2) {
            switch (globalVarNode.getName().charAt(1)) {
                case '_':
                    context.getVariableCompiler().retrieveLastLine();
                    return;
                case '~':
                    context.getVariableCompiler().retrieveBackRef();
                    return;
            }
        }

        context.retrieveGlobalVariable(globalVarNode.getName());
    }
