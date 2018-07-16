    public TransportOutDescription getTransportOut() {
        if (needsToBeReconciled) {
            if (log.isWarnEnabled()) {
                log.warn(myClassName+":getTransportOut(): ****WARNING**** Options.activate(configurationContext) needs to be invoked.");
            }
            //System.out.println(myClassName+":getTransportOut(): ****WARNING**** Options.activate(configurationContext) needs to be invoked.");
        }

        if (transportOut == null && parent != null) {
            return parent.getTransportOut();
        }

        return transportOut;
    }
