            if (jmethod.getExceptionTypes().length > 0) {
                if ((messagePartType = typeTable.getComplexSchemaType(jmethod.getSimpleName() + "Fault")) != null) {
                    namespaceURI = messagePartType.getNamespaceURI();
                    if ((namespacePrefix = (String) messagePartType.getPrefix()) == null &&
                            (namespacePrefix = (String) namespaceMap.get(namespaceURI)) == null) {
                        namespacePrefix = generatePrefix();
                        namespaceMap.put(namespaceURI, namespacePrefix);
                    }
                    //Response Message
                    OMElement responseMessge = fac.createOMElement(
                            MESSAGE_LOCAL_NAME, wsdl);
                    responseMessge.addAttribute(ATTRIBUTE_NAME, jmethod
                            .getSimpleName()
                            + "Fault", null);
                    definitions.addChild(responseMessge);
                    OMElement responsePart = fac.createOMElement(
                            PART_ATTRIBUTE_NAME, wsdl);
                    responseMessge.addChild(responsePart);
                    responsePart.addAttribute(ATTRIBUTE_NAME, "part1", null);

                    responsePart.addAttribute(ELEMENT_ATTRIBUTE_NAME,
                            namespacePrefix + COLON_SEPARATOR
                                    + jmethod.getSimpleName() + "Fault", null);
                }
            }
