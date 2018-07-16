    public void testSOAP12() throws AxisFault {
        OMElement payload = createEnvelope();
        ConfigurationContext configCtx = ConfigurationContextFactory.
                createConfigurationContextFromFileSystem(null, null);
        ServiceClient client = new ServiceClient(configCtx, null);
        Options options = new Options();
        options.setSoapVersionURI(
                SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
        client.setOptions(options);

        OperationClient opClinet = client.createClient(ServiceClient.ANON_OUT_IN_OP);
        opClinet.addMessageContext(prepareTheSOAPEnvelope(payload, options));
        opClinet.execute(true);

        SOAPEnvelope result =
                opClinet.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE).getEnvelope();
        assertEquals("SOAP Version received is not compatible",
                     SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI,
                     result.getNamespace().getNamespaceURI());
    }
