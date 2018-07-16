         try {
             OMElement omElement = testParticalChoiceMaxOccurs4.getOMElement(TestParticalChoiceMaxOccurs4.MY_QNAME,
                      OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestParticalChoiceMaxOccurs4 result = TestParticalChoiceMaxOccurs4.Factory.parse(xmlReader);
            TestParticalChoiceMaxOccursType4Choice[] resultChoices =
                    result.getTestParticalChoiceMaxOccurs4().getTestParticalChoiceMaxOccursType4Choice();
            assertTrue(isEqual(resultChoices[0].getParm1(), testParticalChoiceMaxOccursType4Choice[0].getParm1()));
            assertTrue(isEqual(resultChoices[0].getParm2(), testParticalChoiceMaxOccursType4Choice[0].getParm2()));
            assertTrue(isEqual(resultChoices[0].getParm3(), testParticalChoiceMaxOccursType4Choice[0].getParm3()));

            assertTrue(isEqual(resultChoices[1].getParm1(), testParticalChoiceMaxOccursType4Choice[1].getParm1()));
            assertTrue(isEqual(resultChoices[1].getParm2(), testParticalChoiceMaxOccursType4Choice[1].getParm2()));
            assertTrue(isEqual(resultChoices[1].getParm3(), testParticalChoiceMaxOccursType4Choice[1].getParm3()));

            assertTrue(isEqual(resultChoices[2].getParm1(), testParticalChoiceMaxOccursType4Choice[2].getParm1()));
            assertTrue(isEqual(resultChoices[2].getParm2(), testParticalChoiceMaxOccursType4Choice[2].getParm2()));
            assertTrue(isEqual(resultChoices[2].getParm3(), testParticalChoiceMaxOccursType4Choice[2].getParm3()));

        } catch (XMLStreamException e) {
