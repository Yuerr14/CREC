        try {
            String xPathExpr = seperator + seperator + nsPrefix + colon + echoStringArrayResponse +
                    seperator + ret + seperator + item;
            AXIOMXPath xpath = new AXIOMXPath(xPathExpr);
            addNamespaces(xpath);
            List itemElems = xpath.selectNodes(payload);
            assertNotNull(itemElems);
            assertEquals(itemElems.size(), 3);

            Iterator iter = itemElems.iterator();
            OMElement itemElem = (OMElement)iter.next();
            assertNotNull(itemElem);
            assertEquals(itemElem.getText(), WhiteMesaConstants.ECHO_STRING_ARR_1);
            itemElem = (OMElement)iter.next();
            assertNotNull(itemElem);
            assertEquals(itemElem.getText(), WhiteMesaConstants.ECHO_STRING_ARR_2);
            itemElem = (OMElement)iter.next();
            assertNotNull(itemElem);
            assertEquals(itemElem.getText(), WhiteMesaConstants.ECHO_STRING_ARR_3);

        } catch (JaxenException e) {
