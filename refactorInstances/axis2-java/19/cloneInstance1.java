    public void testEchoString() throws AxisFault {
        configureSystem("echoString");

        Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ConfigurationContext configContext =
                ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
        RPCServiceClient sender = new RPCServiceClient(configContext, null);
        sender.setOptions(options);

        ArrayList args = new ArrayList();
        args.add("foo");
        OMElement response = sender.invokeBlocking(operationName, args.toArray());
        assertEquals(response.getFirstElement().getText(), "foo");
    }
