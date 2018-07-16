            throws AxisFault {
        OperationDescription axisConfig =
                serviceContext.getServiceConfig().getOperation(
                        new QName(axisop));
        if (axisConfig == null) {
            axisConfig = new OperationDescription(new QName(axisop));
            axisConfig.setRemainingPhasesInFlow(
                    operationTemplate.getRemainingPhasesInFlow());
            axisConfig.setPhasesOutFlow(operationTemplate.getPhasesOutFlow());
            axisConfig.setPhasesInFaultFlow(
                    operationTemplate.getPhasesInFaultFlow());
            axisConfig.setPhasesOutFaultFlow(
                    operationTemplate.getPhasesOutFaultFlow());
            serviceContext.getServiceConfig().addOperation(axisConfig);
        }
        MessageContext msgctx = prepareTheSystem(toSend);

        super.invokeNonBlocking(axisConfig, msgctx, callback);
    }
