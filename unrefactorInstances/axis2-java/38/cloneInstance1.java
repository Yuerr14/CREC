    public void testSOAPFaultException1() {
        try {
            SOAPFactory factory = SOAPFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            SOAPFault fault = factory.createFault("This is the fault reason.",
                                                  new QName("http://MyNamespaceURI.org/",
                                                            "My Fault Code"));
        } catch (UnsupportedOperationException e) {
            //Caught expected UnsupportedOperationException
        } catch (SOAPException e) {
            //Caught expected SOAPException
        } catch (IllegalArgumentException e) {
            //Caught expected IllegalArgumentException
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }
