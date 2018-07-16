        } else {
            // FIXME: Missing blockpassnode handling
            final IterNode iterNode = (IterNode) callNode.getIterNode();
            
            final ClosureCallback closureArg = new ClosureCallback() {
                public void compile(Compiler context) {
                    NodeCompilerFactory.getCompiler(iterNode).compile(iterNode, context);
                }
            };
            
            if (callNode.getArgsNode() != null) {
                context.invokeDynamic(callNode.getName(), receiverCallback, argsCallback, CallType.NORMAL, closureArg, false);
            } else {
                context.invokeDynamic(callNode.getName(), receiverCallback, null, CallType.NORMAL, closureArg, false);
            }
        }
