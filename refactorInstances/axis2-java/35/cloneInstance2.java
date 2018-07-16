                if (axisBindingOutMessage != null) {
                    AxisMessage outAxisMessage = axisBindingOutMessage
                            .getAxisMessage();
                    if (outAxisMessage != null) {
                        OMElement output = fac.createOMElement(OUT_PUT_LOCAL_NAME,
                                wsdl);
                        addPolicyAsExtElement(axisBindingOutMessage, output);
                        addExtensionElement(fac, output, SOAP_BODY, SOAP_USE, use,
                                null, targetNamespace, soap12);
                        // addPolicyAsExtElement(PolicyInclude.BINDING_OUTPUT_POLICY,
                        // outAxisMessage.getPolicyInclude(), output);
                        operation.addChild(output);
                        writeSoapHeaders(outAxisMessage, fac, output, soap12);
                    }
                }
