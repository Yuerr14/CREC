    public void testCompositeOnServiceAndPort() {
        QName serviceQN = new QName(namespaceURI, svcLocalPart + uniqueService++);
        
        // Create a service with a composite specifying handlers
        DescriptionBuilderComposite sparseComposite = new DescriptionBuilderComposite();
        HandlerChainsType handlerChainsType = getHandlerChainsType();
        sparseComposite.setHandlerChainsType(handlerChainsType);
        ServiceDelegate.setServiceMetadata(sparseComposite);
        Service service = Service.create(serviceQN);

        // Create a port with a composite specifying different handlers
        DescriptionBuilderComposite portComposite = new DescriptionBuilderComposite();
        HandlerChainsType portHandlerChainsType = getHandlerChainsType("ClientMetadataHandlerChainTest.xml");
        portComposite.setHandlerChainsType(portHandlerChainsType);
        ServiceDelegate.setPortMetadata(portComposite);
        ClientMetadataHandlerChainTestSEI port = service.getPort(ClientMetadataHandlerChainTestSEI.class);
        BindingProvider bindingProvider = (BindingProvider) port;
        Binding binding = (Binding) bindingProvider.getBinding();
        List<Handler> portHandlers = binding.getHandlerChain();

        // If there is a HandlerChainsType composite specified on both the Service and the Port,
        // then the composite specified on the Port should be the one used to associate the 
        // handlers for that Port.
        assertEquals(1, portHandlers.size());
        assertTrue(containsHandlerChainAnnotationHandlers(portHandlers));
    }
