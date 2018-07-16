        try {
            OMElement omElement = testAnyTypeElement.getOMElement(TestAnyTypeElement1.MY_QNAME,
                    OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM Element String ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestAnyTypeElement1 result = TestAnyTypeElement1.Factory.parse(xmlReader);
            assertEquals(result.getTestAnyTypeElement1(),"test");
        } catch (ADBException e) {
