        if (args instanceof BlockPassNode) {
            if (iter != null) {
                throw new SyntaxException(PID.BLOCK_ARG_AND_BLOCK_GIVEN, iter.getPosition(),
                        lexer.getCurrentLine(), "Both block arg and actual block given.");
            }

            BlockPassNode blockPass = (BlockPassNode) args;
            return new FCallNode(position(operation, args), (String) operation.getValue(), blockPass.getArgsNode(), blockPass);
        }
