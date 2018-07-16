        try {
            OMElement omElement = testAnyTypeElement.getOMElement(TestAnyTypeElement5.MY_QNAME,
                    OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM Element String ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestAnyTypeElement5 result = TestAnyTypeElement5.Factory.parse(xmlReader);
            assertEquals(result.getParam1()[0],null);
        } catch (ADBException e) {
