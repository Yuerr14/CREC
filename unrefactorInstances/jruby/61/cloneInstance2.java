        protected FrameSlot findFrameSlot(RubyBinding binding, RubySymbol symbol) {
            final String symbolString = symbol.toString();

            MaterializedFrame frame = binding.getFrame();

            while (frame != null) {
                final FrameSlot frameSlot = frame.getFrameDescriptor().findFrameSlot(symbolString);

                if (frameSlot != null) {
                    return frameSlot;
                }

                frame = RubyArguments.getDeclarationFrame(frame.getArguments());
            }

            return binding.getFrame().getFrameDescriptor().addFrameSlot(symbolString);
        }
