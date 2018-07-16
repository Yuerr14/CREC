        } else {
            // FIXME: Missing blockpasnode stuff here
            
            final IterNode iterNode = (IterNode) fcallNode.getIterNode();
            
            final ClosureCallback closureArg = new ClosureCallback() {
                public void compile(Compiler context) {
                    NodeCompilerFactory.getCompiler(iterNode).compile(iterNode, context);
                }
            };

            if (fcallNode.getArgsNode() != null) {
                context.invokeDynamic(fcallNode.getName(), null, argsCallback, CallType.FUNCTIONAL, closureArg, false);
            } else {
                context.invokeDynamic(fcallNode.getName(), null, null, CallType.FUNCTIONAL, closureArg, false);
            }
        }
