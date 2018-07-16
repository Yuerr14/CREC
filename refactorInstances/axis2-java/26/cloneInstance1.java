            && !(implPDCList == null || implPDCList.isEmpty())) {
            String message = "Validation error: SEI indicates no parameters but implementation method specifies parameters: "
                + implPDCList
                + "; Implementation class: "
                + composite.getClassName()
                + "; Method name: " + seiMDC.getMethodName() + "; Endpoint Interface: " + className;
            throw ExceptionFactory.makeWebServiceException(message);
        } else if ((seiPDCList != null && !seiPDCList.isEmpty())
