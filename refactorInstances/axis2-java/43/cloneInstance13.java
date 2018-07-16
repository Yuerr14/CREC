       try {
           OMElement omElement =
                   testGroupChoiceElement.getOMElement(TestChoiceGroupElement.MY_QNAME, OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String ==> " + omElementString);
            XMLStreamReader xmlReader =
                    StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestChoiceGroupElement result = TestChoiceGroupElement.Factory.parse(xmlReader);
            assertEquals(result.getTestChoiceGroup().getChoiceParam1(),"choiceParam1");
        } catch (XMLStreamException e) {
