        if (values[0] != null) {
            AxisConfiguration registry =
                    configurationContext.getAxisConfiguration();

            service = registry.getService(values[0]);

            // If the axisService is not null we get the binding that the request came to and
            // add it as a property to the messageContext
            if (service != null) {
                Map endpoints = service.getEndpoints();
                if (endpoints != null) {
                    if (endpoints.size() == 1) {
                        messageContext.setProperty(WSDL2Constants.ENDPOINT_LOCAL_NAME,
                                                   endpoints.get(
                                                           service.getEndpointName()));
                    } else {
                        String endpointName = values[0].substring(values[0].indexOf(".") + 1);
                        messageContext.setProperty(WSDL2Constants.ENDPOINT_LOCAL_NAME,
                                                   endpoints.get(endpointName));
                    }
                }
            }
        }
