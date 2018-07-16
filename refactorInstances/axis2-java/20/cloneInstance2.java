            if (inAxisMessage != null) {
                if (inAxisMessage.getElementQName() == null) {
                    // method accept empty SOAPbody
                    method.invoke(obj, new Object[0]);
                } else {
                    elementQName = inAxisMessage.getElementQName();
                    messageNameSpace = elementQName.getNamespaceURI();
                    OMNamespace namespace = methodElement.getNamespace();
                    if (messageNameSpace != null) {
                        if (namespace == null ||
                                !messageNameSpace.equals(namespace.getNamespaceURI())) {
                            throw new AxisFault("namespace mismatch require " +
                                    messageNameSpace +
                                    " found " +
                                    methodElement.getNamespace().getNamespaceURI());
                        }
                    } else if (namespace != null) {
                        throw new AxisFault(
                                "namespace mismatch. Axis Oepration expects non-namespace " +
                                        "qualified element. But received a namespace qualified element");
                    }

                    Object[] objectArray = RPCUtil.processRequest(methodElement, method,
                                                                  inMessage
                                                                          .getAxisService().getObjectSupplier());
                    method.invoke(obj, objectArray);
                }

            }
