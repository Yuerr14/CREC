            if (mep == null) {

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

                addAttribute(doc, "namespace", opNS, methodElement);
                String style = axisOperation.getStyle();
                addAttribute(doc, "style", style, methodElement);
                addAttribute(doc, "dbsupportname", portTypeName + localPart + DATABINDING_SUPPORTER_NAME_SUFFIX,
                        methodElement);


                addAttribute(doc, "mep", Utils.getAxisSpecifMEPConstant(axisOperation.getMessageExchangePattern()) + "", methodElement);
                addAttribute(doc, "mepURI", axisOperation.getMessageExchangePattern(), methodElement);


                addSOAPAction(doc, methodElement, axisOperation);
                //add header ops for input
                addHeaderOperations(soapHeaderInputParameterList, axisOperation, true);
                //add header ops for output
                addHeaderOperations(soapHeaderOutputParameterList, axisOperation, false);

                PolicyInclude policyInclude = axisOperation.getPolicyInclude();
                Policy policy = policyInclude.getPolicy();
                if (policy != null) {
                    try {
                        addAttribute(doc, "policy", PolicyUtil.policyComponentToString(policy), methodElement);
                    } catch (Exception ex) {
                        throw new RuntimeException("can't serialize the policy to a String " , ex);
                    }
                }

                methodElement.appendChild(getInputElement(doc, axisOperation, soapHeaderInputParameterList));
                methodElement.appendChild(getOutputElement(doc, axisOperation, soapHeaderOutputParameterList));
                methodElement.appendChild(getFaultElement(doc, axisOperation));

                rootElement.appendChild(methodElement);
            } else {
