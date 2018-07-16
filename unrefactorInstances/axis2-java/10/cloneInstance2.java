            }else if (msgContext.isDoingREST()) {
                Reader reader = new InputStreamReader(inStream);
                XMLStreamReader xmlreader =
                    XMLInputFactory.newInstance().createXMLStreamReader(reader);
                SOAPFactory soapFactory = new SOAP11Factory();
                builder = new StAXOMBuilder(xmlreader);
                builder.setOmbuilderFactory(soapFactory);
                envelope = soapFactory.getDefaultEnvelope();
                envelope.getBody().addChild(builder.getDocumentElement());
            } else {
