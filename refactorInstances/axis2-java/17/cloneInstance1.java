    public void testMulitref1() throws AxisFault {
        configureSystem("echoString");
        OMFactory fac = OMAbstractFactory.getOMFactory();

        OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "my");
        OMElement method = fac.createOMElement("echoString", omNs);
        OMElement value = fac.createOMElement("arg0", null);
        value.addAttribute(fac.createOMAttribute("href", null, "#1"));
        method.addChild(value);
        SOAPFactory factory = OMAbstractFactory.getSOAP11Factory();
        SOAPEnvelope envelope = factory.getDefaultEnvelope();
        envelope.getBody().addChild(method);

        OMElement ref = fac.createOMElement("reference", null);
        ref.addAttribute(fac.createOMAttribute("id", null, "1"));
        ref.setText("hello Axis2");
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


        assertEquals(env.getBody().getFirstElement().getFirstElement().getText(), "hello Axis2");
    }
