       try {
           OMElement omElement =
                   testChoiceNestedGroupElement.getOMElement(testChoiceNestedGroupElement.MY_QNAME, OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String ==> " + omElementString);
            XMLStreamReader xmlReader =
                    StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestChoiceNestedGroupElement result = TestChoiceNestedGroupElement.Factory.parse(xmlReader);
            assertEquals(result.getTestChoiceNestedGroup().getTestChoiceGroup().getChoiceParam1(),"choiceParam1");
        } catch (XMLStreamException e) {
