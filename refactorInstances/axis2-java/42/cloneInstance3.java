        try {
            OMElement omElement = testAnyTypeElement.getOMElement(TestAnyTypeElement10.MY_QNAME,
                    OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM Element String ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestAnyTypeElement10 result = TestAnyTypeElement10.Factory.parse(xmlReader);
            assertEquals(result.getParam1(),"test");
        } catch (ADBException e) {
