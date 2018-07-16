    public void testEchoStringWSGEN1() {
        TestLogger.logger.debug("------------------------------");
        TestLogger.logger.debug("Test : " + getName());
        try{
            String request = "hello world";
            
            DocLitWrapService service = new DocLitWrapService();
            DocLitWrap proxy = service.getDocLitWrapPort();
            BindingProvider p = (BindingProvider) proxy;
            p.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, axisEndpoint);
            String response = proxy.echoStringWSGEN1(request);
            assertTrue(response.equals(request));
            TestLogger.logger.debug("------------------------------");
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }
