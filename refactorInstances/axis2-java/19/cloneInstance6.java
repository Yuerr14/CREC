    public void testEchoBool() throws AxisFault {
        configureSystem("echoBool");

        Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ConfigurationContext configContext =
                ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
        RPCServiceClient sender = new RPCServiceClient(configContext, null);
        sender.setOptions(options);

        ArrayList args = new ArrayList();
        args.add("true");

        OMElement response = sender.invokeBlocking(operationName, args.toArray());
        assertEquals(Boolean.valueOf(response.getFirstElement().getText()).booleanValue(), true);
    }
