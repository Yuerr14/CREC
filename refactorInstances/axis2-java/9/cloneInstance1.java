    public TransportListener getListener() {
        if (needsToBeReconciled) {
            if (log.isWarnEnabled()) {
                log.warn(myClassName+":getListener(): ****WARNING**** Options.activate(configurationContext) needs to be invoked.");
            }
            //System.out.println(myClassName+":getListener(): ****WARNING**** Options.activate(configurationContext) needs to be invoked.");
        }

        if (listener == null && parent != null) {
            return parent.getListener();
        }
        return listener;
    }
