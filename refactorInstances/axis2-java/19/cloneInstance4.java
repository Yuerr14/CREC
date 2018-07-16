    public void testByteArray() throws AxisFault {
        configureSystem("testByteArray");
        ConfigurationContext configContext =
                ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
        RPCServiceClient sender = new RPCServiceClient(configContext, null);

        Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
        sender.setOptions(options);
        ArrayList args = new ArrayList();
        String hello = "hello";
        args.add(hello.getBytes());

        OMElement response = sender.invokeBlocking(operationName, args.toArray());
        assertEquals(response.getFirstElement().getText(), hello);
    }
