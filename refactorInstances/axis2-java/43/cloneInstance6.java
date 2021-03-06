        try {
            OMElement omElement = testAnyTypeElement.getOMElement(TestAnyTypeElement4.MY_QNAME,
                    OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM Element String ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestAnyTypeElement4 result = TestAnyTypeElement4.Factory.parse(xmlReader);
            assertEquals(result.getParam1()[0],"test");
        } catch (ADBException e) {
