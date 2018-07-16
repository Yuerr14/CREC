        try {
            ssc.configureBlocking(false);
            selector = Selector.open();
            SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
            
            context.getThread().beforeBlockingCall();
            while (true) {
                int selected = selector.select();
                if (selected == 0) {
                    // we were woken up without being selected...poll for thread events and go back to sleep
                    getRuntime().getCurrentContext().pollThreadEvents();
                } else {
                    try {
                        // otherwise one key has been selected (ours) so we get the channel and hand it off
                        socket.initSocket(new ChannelDescriptor(ssc.accept(), RubyIO.getNewFileno(), new ModeFlags(ModeFlags.RDWR), new FileDescriptor()));
                    } catch (InvalidValueException ex) {
                        throw getRuntime().newErrnoEINVALError();
                    }
                    return socket;
                }
            }
        } catch(IOException e) {
