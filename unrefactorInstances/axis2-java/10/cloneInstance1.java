						&& Constants.VALUE_TRUE.equals(enable)) {
					msgContext.setDoingREST(true);
					SOAPFactory soapFactory = new SOAP11Factory();
                    Reader reader = new InputStreamReader(in);
                    XMLStreamReader xmlreader = XMLInputFactory.newInstance()
                            .createXMLStreamReader(reader);
					builder = new StAXOMBuilder(xmlreader);
					builder.setOmbuilderFactory(soapFactory);
					envelope = soapFactory.getDefaultEnvelope();
					envelope.getBody().addChild(builder.getDocumentElement());
				}
