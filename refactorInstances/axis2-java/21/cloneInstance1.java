            throws AxisFault {
        Method method = null;
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(inMessage);

            Class ImplClass = obj.getClass();

            AxisOperation op = inMessage.getOperationContext().getAxisOperation();
            AxisService service = inMessage.getAxisService();
            OMElement methodElement = inMessage.getEnvelope().getBody()
                    .getFirstElement();
            AxisMessage inAxisMessage = op.getMessage(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            String messageNameSpace = null;
            QName elementQName;

            method = (Method)(op.getParameterValue("myMethod"));
            if (method == null) {
                String methodName = op.getName().getLocalPart();
                Method[] methods = ImplClass.getMethods();

                for (int i = 0; i < methods.length; i++) {
                    if (methods[i].getName().equals(methodName)) {
                        method = methods[i];
                        op.addParameter("myMethod", method);
                        break;
                    }
                }
                if (method == null) {
                    throw new AxisFault("No such method '" + methodName +
                            "' in class " + ImplClass.getName());
                }
            }
            Object resObject = null;
            if (inAxisMessage != null) {
                if (inAxisMessage.getElementQName() == null) {
                    // method accept empty SOAPbody
                    resObject = method.invoke(obj, new Object[0]);
                } else {
                    elementQName = inAxisMessage.getElementQName();
                    messageNameSpace = elementQName.getNamespaceURI();
                    OMNamespace namespace = methodElement.getNamespace();
                    if (messageNameSpace != null) {
                        if (namespace == null) {
                            throw new AxisFault("namespace mismatch require " +
                                    messageNameSpace +
                                    " found none");
                        }
                        if (!messageNameSpace.equals(namespace.getNamespaceURI())) {
                            throw new AxisFault("namespace mismatch require " +
                                    messageNameSpace +
                                    " found " + methodElement.getNamespace().getNamespaceURI());
                        }
                    } else if (namespace != null) {
                        throw new AxisFault(
                                "namespace mismatch. Axis Oepration expects non-namespace " +
                                        "qualified element. But received a namespace qualified element");
                    }

                    Object[] objectArray = RPCUtil.processRequest(methodElement, method,
                                                                  inMessage
                                                                          .getAxisService().getObjectSupplier());
                    resObject = method.invoke(obj, objectArray);
                }

            }


            SOAPFactory fac = getSOAPFactory(inMessage);

            // Handling the response
            AxisMessage outaxisMessage = op.getMessage(WSDLConstants.MESSAGE_LABEL_OUT_VALUE);
            if (outaxisMessage != null) {
                messageNameSpace = outaxisMessage.getElementQName().getNamespaceURI();
            }

            OMNamespace ns = fac.createOMNamespace(messageNameSpace,
                                                   service.getSchemaTargetNamespacePrefix());
            SOAPEnvelope envelope = fac.getDefaultEnvelope();
            OMElement bodyContent = null;
            RPCUtil.processResponse(resObject, service,
                                    method, envelope, fac, ns,
                                    bodyContent, outMessage);
            outMessage.setEnvelope(envelope);
        } catch (InvocationTargetException e) {
            String msg = null;
            Throwable cause = e.getCause();
            if (cause != null) {
                msg = cause.getMessage();
            }
            if (msg == null) {
                msg = "Exception occurred while trying to invoke service method " +
                        method.getName();
            }
            log.error(msg, e);
            if (cause instanceof AxisFault) {
                throw (AxisFault)cause;
            }
            throw new AxisFault(msg, e);
        } catch(RuntimeException e) {
            throw AxisFault.makeFault(e);
        } catch (Exception e) {
            String msg = "Exception occurred while trying to invoke service method " +
                    method.getName();
            log.error(msg, e);
            throw AxisFault.makeFault(e);
        }
    }
