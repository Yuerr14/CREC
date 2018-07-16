            && !(implPDCList != null && !implPDCList.isEmpty())) {
            String message = "Validation error: SEI indicates parameters " + seiPDCList
                + " but implementation method specifies no parameters; Implementation class: "
                + composite.getClassName() + "; Method name: " + seiMDC.getMethodName()
                + "; Endpoint Interface: " + className;
            throw ExceptionFactory.makeWebServiceException(message);
        } else if (seiPDCList.size() != implPDCList.size()) {
