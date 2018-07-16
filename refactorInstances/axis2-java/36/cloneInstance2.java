        if (faultDescs != null) {
            for (FaultDescription faultDesc : faultDescs) {
        
                AxisMessage faultMessage = new AxisMessage();
                String faultName = faultDesc.getName();
                faultMessage.setName(faultName);
                
                String faultAction = 
                        WSDL11ActionHelper.getFaultActionFromStringInformation( targetNS, 
                                        portTypeName, 
                                        operationName, 
                                        faultMessage.getName());
                
                newAxisOperation.addFaultAction(faultMessage.getName(), faultAction);
                newAxisOperation.setFaultMessages(faultMessage);
            }
        }
