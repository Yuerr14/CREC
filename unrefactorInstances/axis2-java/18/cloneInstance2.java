        } catch (Exception e) {
            assertNotNull(e);
            if (CHECK_VERSIONMISMATCH) {
                assertTrue("Expected SOAPFaultException, but received: "+ e.getClass(),
                           e instanceof SOAPFaultException);
                SOAPFaultException sfe = (SOAPFaultException) e;

                SOAPFault fault = sfe.getFault();

                assertTrue("SOAPFault is null ",
                           fault != null);
                QName faultCode = sfe.getFault().getFaultCodeAsQName();


                assertTrue("Expected VERSION MISMATCH but received: "+ faultCode,
                           new QName(SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE, "VersionMismatch", SOAPConstants.SOAP_ENV_PREFIX).equals(faultCode));

            }
        }
