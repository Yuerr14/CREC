        try {
            ssc.configureBlocking(false);
            selector = Selector.open();
            SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
            
            int selected = selector.selectNow();
            if (selected == 0) {
                // no connection immediately accepted, let them try again
                throw getRuntime().newErrnoEAGAINError("Resource temporarily unavailable");
            } else {
                try {
                    // otherwise one key has been selected (ours) so we get the channel and hand it off
                    socket.initSocket(new ChannelDescriptor(ssc.accept(), RubyIO.getNewFileno(), new ModeFlags(ModeFlags.RDWR), new FileDescriptor()));
                } catch (InvalidValueException ex) {
                    throw getRuntime().newErrnoEINVALError();
                }
                return socket;
            }
        } catch(IOException e) {
