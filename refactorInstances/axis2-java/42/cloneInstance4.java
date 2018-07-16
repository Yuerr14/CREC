         try {
             omElement = testElement.getOMElement(TestElement2.MY_QNAME, OMAbstractFactory.getOMFactory());
             String omElementString = omElement.toStringWithConsume();
            System.out.println("OMElement ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestElement2 result = TestElement2.Factory.parse(xmlReader);
            assertEquals(result.getAttribute1(),1);
        } catch (Exception e) {
