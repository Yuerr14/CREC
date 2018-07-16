        try {
            OMElement omElement = testAnyTypeElement.getOMElement(TestAnyTypeElement6.MY_QNAME,
                    OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM Element String ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestAnyTypeElement6 result = TestAnyTypeElement6.Factory.parse(xmlReader);
            assertEquals(result.getParam1()[0],"test1");
            assertEquals(result.getParam1()[1],"test2");
        } catch (ADBException e) {
