    public void testSOAPFaultException2() {
        try {
            SOAPFactory factory = SOAPFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            SOAPFault sf = factory.createFault("This is the fault reason.",
                                               new QName("http://MyNamespaceURI.org/",
                                                         "My Fault Code"));
            fail("Did not throw expected SOAPException");
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
