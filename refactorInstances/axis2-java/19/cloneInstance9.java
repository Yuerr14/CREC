    public void testmulReturn() throws AxisFault {
        configureSystem("mulReturn");

        Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ConfigurationContext configContext =
                ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
        RPCServiceClient sender = new RPCServiceClient(configContext, null);
        sender.setOptions(options);

        ArrayList args = new ArrayList();
        args.add("foo");


        OMElement element = sender.invokeBlocking(operationName, args.toArray());
        System.out.println("element = " + element);
//        assertEquals(response.getFirstElement().getText(), "foo");
    }
