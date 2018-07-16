    public void testEchoByte() throws AxisFault {
        configureSystem("echoByte");

        Options options = new Options();

        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ConfigurationContext configContext =
                ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
        RPCServiceClient sender = new RPCServiceClient(configContext, null);
        sender.setOptions(options);

        ArrayList args = new ArrayList();
        args.add("1");
        OMElement response = sender.invokeBlocking(operationName, args.toArray());
        assertEquals(Byte.parseByte(response.getFirstElement().getText()), 1);
    }
