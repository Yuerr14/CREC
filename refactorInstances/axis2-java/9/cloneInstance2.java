    public TransportInDescription getTransportIn() {
        if (needsToBeReconciled) {
            if (log.isWarnEnabled()) {
                log.warn(myClassName+":getTransportIn(): ****WARNING**** Options.activate(configurationContext) needs to be invoked.");
            }
            //System.out.println(myClassName+":getTransportIn(): ****WARNING**** Options.activate(configurationContext) needs to be invoked.");
        }

        if (transportIn == null && parent != null) {
            return parent.getTransportIn();
        }
        return transportIn;
    }
