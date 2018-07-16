        } else if (seiReturnValue != null && implReturnValue == null) {
            String message = "Validation error: SEI indicates return value " + seiReturnValue
                + " but implementation method specifies no return value; Implementation class: "
                + composite.getClassName() + "; Method name: " + seiMDC.getMethodName()
                + "; Endpoint Interface: " + className;
            throw ExceptionFactory.makeWebServiceException(message);
        } else if (!seiReturnValue.equals(implReturnValue)) {
