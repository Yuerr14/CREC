    public void testDivide() throws AxisFault {
        configureSystem("divide");

        Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ConfigurationContext configContext =
                ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
        RPCServiceClient sender = new RPCServiceClient(configContext, null);
        sender.setOptions(options);


        ArrayList args = new ArrayList();
        args.add("10");
        args.add("0");
        OMElement response = sender.invokeBlocking(operationName, args.toArray());
        assertEquals(response.getFirstElement().getText(), "INF");
    }
