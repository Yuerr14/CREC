    public void testOneWay() {
        try {
            TestLogger.logger.debug("----------------------------------");
            TestLogger.logger.debug("test: " + getName());
            
            AddNumbersHandlerService service = new AddNumbersHandlerService();
            AddNumbersHandlerPortType proxy = service.getAddNumbersHandlerPort();
            
            BindingProvider bp = (BindingProvider) proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, 
                    axisEndpoint);
            proxy.oneWayInt(11);
            TestLogger.logger.debug("----------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }       
    }
