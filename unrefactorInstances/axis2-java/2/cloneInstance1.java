    public OperationContext findOperationContext(MessageContext msgContext, ServiceContext serviceContext) throws AxisFault {
        OperationContext operationContext = null;

        if (null == msgContext.getRelatesTo()) {
            //Its a new incomming message so get the factory to create a new
            // one
            operationContext =
                    OperationContextFactory.createOperationContext(
                            getAxisSpecifMEPConstant(),  this, serviceContext);

        } else {
            // So this message is part of an ongoing MEP
            //			operationContext =
            ConfigurationContext configContext = msgContext.getSystemContext();
            operationContext =
                    configContext.getOperationContext(
                            msgContext.getRelatesTo().getValue());

            if (null == operationContext) {
                throw new AxisFault(Messages.getMessage("cannotCorrealteMsg",
                        this.getName().toString(),msgContext.getRelatesTo().getValue()));
            }

        }

        registerOperationContext(msgContext, operationContext);

        return operationContext;

    }
