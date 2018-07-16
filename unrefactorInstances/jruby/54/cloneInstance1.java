        if (argsNode instanceof BlockPassNode) {
            if (iter != null) {
                throw new SyntaxException(PID.BLOCK_ARG_AND_BLOCK_GIVEN, iter.getPosition(),
                        lexer.getCurrentLine(), "Both block arg and actual block given.");
            }

            BlockPassNode blockPass = (BlockPassNode) argsNode;
            return new CallNode(position(receiver, argsNode), receiver, (String) name.getValue(), blockPass.getArgsNode(), blockPass);
        }
