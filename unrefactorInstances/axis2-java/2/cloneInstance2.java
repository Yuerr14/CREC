    public OperationContext findForExistingOperationContext(MessageContext msgContext) throws AxisFault {
        OperationContext operationContext = null;

        if (null == msgContext.getRelatesTo()) {
            return null;
        } else {
            // So this message is part of an ongoing MEP
            //			operationContext =
            ConfigurationContext configContext = msgContext.getSystemContext();
            operationContext = configContext.getOperationContext(msgContext.getRelatesTo().getValue());

            if (null == operationContext) {
                throw new AxisFault(Messages.getMessage("cannotCorrealteMsg",
                        this.getName().toString(),msgContext.getRelatesTo().getValue()));
            }

        }

        registerOperationContext(msgContext, operationContext);

        return operationContext;

    }
