    private boolean isNewSunBehaviorSupported() {

        if (log.isDebugEnabled()) {
            log.debug("isNewSunBehavior: Validating that JDK version can be used");

        }
        
        boolean versionValid = false;

        try {
            
            String wsGenVersion = WSToolingUtils.getWsGenVersion();
            
            versionValid = WSToolingUtils.isValidVersion(wsGenVersion);
            
            if (log.isDebugEnabled()) {
                log.debug("isNewSunBehavior: versionValid is: " +versionValid);
            }
            
            if (!versionValid) {

                if (log.isDebugEnabled()) {
                    log.debug("New Sun tooling behavior is not supported with this version of the JDK");
                }
            }

            // We don't want to affect existing systems, if anything goes
            // wrong just display
            // a warning and default to old behavior
        } catch (ClassNotFoundException e) {
            if (log.isDebugEnabled()) {
                log.debug(" Unable to determine WsGen version being used");
            }
        } catch (IOException ioex) {
            if (log.isDebugEnabled()) {
                log.debug(" Unable to determine WsGen version being used");
            }
        }
        
        return versionValid;
    }
