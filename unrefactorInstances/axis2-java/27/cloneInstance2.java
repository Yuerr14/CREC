            } else {
                //mep is present - we move ahead only if the given mep matches the mep of this operation

                if (mep.equals(axisOperation.getMessageExchangePattern())) {
                    //at this point we know it's true
                    opsFound = true;
                    List soapHeaderInputParameterList = new ArrayList();
                    List soapHeaderOutputParameterList = new ArrayList();
                    methodElement = doc.createElement("method");
                    String localPart = axisOperation.getName().getLocalPart();
                    String opCName = makeCClassName(localPart);
                    String opNS = axisOperation.getName().getNamespaceURI();

                    addAttribute(doc, "name", opCName, methodElement);
                    addAttribute(doc, "localpart", localPart, methodElement);
                    addAttribute(doc, "qname", localPart+ "|"+ opNS, methodElement);

                    addAttribute(doc, "namespace", axisOperation.getName().getNamespaceURI(), methodElement);
                    addAttribute(doc, "style", axisOperation.getStyle(), methodElement);
                    addAttribute(doc, "dbsupportname", portTypeName + localPart + DATABINDING_SUPPORTER_NAME_SUFFIX,
                            methodElement);

                    addAttribute(doc, "mep", Utils.getAxisSpecifMEPConstant(axisOperation.getMessageExchangePattern()) + "", methodElement);
                    addAttribute(doc, "mepURI", axisOperation.getMessageExchangePattern(), methodElement);


                    addSOAPAction(doc, methodElement, axisOperation);
                    addHeaderOperations(soapHeaderInputParameterList, axisOperation, true);
                    addHeaderOperations(soapHeaderOutputParameterList, axisOperation, false);

                    /*
                     * Setting the policy of the operation
                     */

                    Policy policy = axisOperation.getPolicyInclude().getPolicy();
                    if (policy != null) {
                        try {
                        addAttribute(doc, "policy",
                                PolicyUtil.policyComponentToString(policy),
                                methodElement);
                        } catch (Exception ex) {
                            throw new RuntimeException("can't serialize the policy to a String", ex);
                        }
                    }


                    methodElement.appendChild(getInputElement(doc,
                            axisOperation, soapHeaderInputParameterList));
                    methodElement.appendChild(getOutputElement(doc,
                            axisOperation, soapHeaderOutputParameterList));
                    methodElement.appendChild(getFaultElement(doc,
                            axisOperation));

                    rootElement.appendChild(methodElement);
                    //////////////////////
                }

            }
