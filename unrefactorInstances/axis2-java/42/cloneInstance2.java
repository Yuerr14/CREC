       try {
            OMElement omElement = testParticalChoiceMaxOccurs5.getOMElement(TestParticalChoiceMaxOccurs5.MY_QNAME,
                   OMAbstractFactory.getOMFactory());
            String omElementString = omElement.toStringWithConsume();
            System.out.println("OM String ==> " + omElementString);
            XMLStreamReader xmlReader = StAXUtils.createXMLStreamReader(new ByteArrayInputStream(omElementString.getBytes()));
            TestParticalChoiceMaxOccurs5 result = TestParticalChoiceMaxOccurs5.Factory.parse(xmlReader);
            TestParticalChoiceMaxOccursType5Choice[] resultChoices =
                    result.getTestParticalChoiceMaxOccurs5().getTestParticalChoiceMaxOccursType5Choice();
            assertTrue(isEqual(resultChoices[0].getParm1(), testParticalChoiceMaxOccursType5Choice[0].getParm1()));
            assertTrue(isEqual(resultChoices[0].getParm2(), testParticalChoiceMaxOccursType5Choice[0].getParm2()));
            assertTrue(isEqual(resultChoices[0].getParm3(), testParticalChoiceMaxOccursType5Choice[0].getParm3()));

            assertTrue(isEqual(resultChoices[1].getParm1(), testParticalChoiceMaxOccursType5Choice[1].getParm1()));
            assertTrue(isEqual(resultChoices[1].getParm2(), testParticalChoiceMaxOccursType5Choice[1].getParm2()));
            assertTrue(isEqual(resultChoices[1].getParm3(), testParticalChoiceMaxOccursType5Choice[1].getParm3()));

            assertTrue(isEqual(resultChoices[2].getParm1(), testParticalChoiceMaxOccursType5Choice[2].getParm1()));
            assertTrue(isEqual(resultChoices[2].getParm2(), testParticalChoiceMaxOccursType5Choice[2].getParm2()));
            assertTrue(isEqual(resultChoices[2].getParm3(), testParticalChoiceMaxOccursType5Choice[2].getParm3()));

        } catch (XMLStreamException e) {
