        try {
            OMElement omElement = testExtension.getOMElement(TestExtension.MY_QNAME, OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestExtension result = TestExtension.Factory.parse(xmlReader);
            
        } catch (ADBException e) {
