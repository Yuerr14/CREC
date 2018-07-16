    public void testEchoInt() throws AxisFault {
        configureSystem("echoInt");

        Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ConfigurationContext configContext =
                ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
        RPCServiceClient sender = new RPCServiceClient(configContext, null);
        sender.setOptions(options);

        ArrayList args = new ArrayList();
        args.add("100");

        OMElement response = sender.invokeBlocking(operationName, args.toArray());
        assertEquals(Integer.parseInt(response.getFirstElement().getText()), 100);
    }
