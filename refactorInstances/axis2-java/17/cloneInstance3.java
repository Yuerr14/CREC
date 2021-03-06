    public void testaddSameRef() throws AxisFault {
        configureSystem("add");
        OMFactory fac = OMAbstractFactory.getOMFactory();

        OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "my");
        OMElement method = fac.createOMElement("add", omNs);
        OMElement value = fac.createOMElement("arg0", null);
        value.addAttribute(fac.createOMAttribute("href", null, "#1"));
        method.addChild(value);

        OMElement value2 = fac.createOMElement("arg1", null);
        value2.addAttribute(fac.createOMAttribute("href", null, "#1"));
        method.addChild(value2);

        SOAPFactory factory = OMAbstractFactory.getSOAP11Factory();
        SOAPEnvelope envelope = factory.getDefaultEnvelope();
        envelope.getBody().addChild(method);

        OMElement ref = fac.createOMElement("reference", null);
        ref.addAttribute(fac.createOMAttribute("id", null, "1"));
        ref.setText("10");
        envelope.getBody().addChild(ref);

        Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ConfigurationContext configContext = ConfigurationContextFactory
                .createConfigurationContextFromFileSystem(null, null);
        RPCServiceClient rpcClient = new RPCServiceClient(configContext, null);
        rpcClient.setOptions(options);
        MessageContext reqMessageContext = configContext.createMessageContext();
        OperationClient opClinet = rpcClient.createClient(ServiceClient.ANON_OUT_IN_OP);
        opClinet.setOptions(options);
        reqMessageContext.setEnvelope(envelope);

        opClinet.addMessageContext(reqMessageContext);
        opClinet.execute(true);

        MessageContext responseMessageContx =
                opClinet.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
        SOAPEnvelope env = responseMessageContx.getEnvelope();

        assertEquals(env.getBody().getFirstElement().getFirstElement().getText(), "20");
    }
