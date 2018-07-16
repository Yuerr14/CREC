         try {
             OMElement omElement =
                      testInnerParticle3.getOMElement(TestInnerParticle3.MY_QNAME, OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestInnerParticle3 result = TestInnerParticle3.Factory.parse(xmlReader);
            assertEquals(result.getParam4(), "Param4");
        } catch (XMLStreamException e) {
