            throws AxisFault {
        return new Request() {
            @Override
            public void execute() throws AxisFault {
                HttpClient httpClient = getHttpClient(msgContext);
        
                /*
                 * Same deal - this value never gets used, why is it here? --Glen String
                 * charEncoding = (String)
                 * msgContext.getProperty(Constants.Configuration
                 * .CHARACTER_SET_ENCODING);
                 * 
                 * if (charEncoding == null) { charEncoding =
                 * MessageContext.DEFAULT_CHAR_SET_ENCODING; }
                 */
        
                PutMethod putMethod = new PutMethod();
                MessageFormatter messageFormatter = populateCommonProperties(msgContext, url, putMethod,
                        httpClient, soapActionString);
        
                putMethod.setRequestEntity(new AxisRequestEntityImpl(messageFormatter, msgContext, format,
                        soapActionString, chunked, isAllowedRetry));
        
                if (!httpVersion.equals(HTTPConstants.HEADER_PROTOCOL_10) && chunked) {
                    putMethod.setContentChunked(true);
                }
        
                String soapAction = messageFormatter.formatSOAPAction(msgContext, format, soapActionString);
                if (soapAction != null && !msgContext.isDoingREST()) {
                    putMethod.setRequestHeader(HTTPConstants.HEADER_SOAP_ACTION, soapAction);
                }
        
                /*
                 * main excecution takes place..
                 */
                try {
                    executeMethod(httpClient, msgContext, url, putMethod);
                    handleResponse(msgContext, putMethod);
                } catch (IOException e) {
                    log.info("Unable to sendViaPut to url[" + url + "]", e);
                    throw AxisFault.makeFault(e);
                } finally {
                    cleanup(msgContext, putMethod);
                }
            }
        };
    }
