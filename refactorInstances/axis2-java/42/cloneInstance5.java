        try {
            omElement = testElement.getOMElement(TestElement4.MY_QNAME, OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OMElement ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestElement4 result = TestElement4.Factory.parse(xmlReader);
            assertEquals(result.getAttribute1(),"test");
        } catch (Exception e) {
