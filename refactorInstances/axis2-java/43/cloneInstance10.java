        try {
            OMElement omElement = testFuzzyDateType.getOMElement(
                    TestFuzzyDateType.MY_QNAME,OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM Element ==> " + omElementString);
            XMLStreamReader xmlReader =
                    StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestFuzzyDateType result = TestFuzzyDateType.Factory.parse(xmlReader);
        } catch (ADBException e) {
