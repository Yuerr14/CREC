            throws AxisFault {
        return new Request() {
            @Override
            public void execute() throws AxisFault {
                AbstractHttpClient httpClient = getHttpClient(msgContext);
        
                /*
                 * Same deal - this value never gets used, why is it here? --Glen String
                 * charEncoding = (String)
                 * msgContext.getProperty(Constants.Configuration
                 * .CHARACTER_SET_ENCODING);
                 *
                 * if (charEncoding == null) { charEncoding =
                 * MessageContext.DEFAULT_CHAR_SET_ENCODING; }
                 */
        
                HttpPut putMethod = new HttpPut();
                MessageFormatter messageFormatter = populateCommonProperties(msgContext, url, putMethod,
                                                                             httpClient, soapActionString);
                AxisRequestEntityImpl requestEntity =
                        new AxisRequestEntityImpl(messageFormatter, msgContext, format,
                                                  soapActionString, chunked, isAllowedRetry);
                putMethod.setEntity(requestEntity);
        
                if (!httpVersion.equals(HTTPConstants.HEADER_PROTOCOL_10) && chunked) {
                    requestEntity.setChunked(chunked);
                }
        
                String soapAction = messageFormatter.formatSOAPAction(msgContext, format, soapActionString);
                if (soapAction != null && !msgContext.isDoingREST()) {
                    putMethod.setHeader(HTTPConstants.HEADER_SOAP_ACTION, soapAction);
                }
        
                /*
                 * main execution takes place..
                 */
                HttpResponse response = null;
                try {
                    response = executeMethod(httpClient, msgContext, url, putMethod);
                    handleResponse(msgContext, response);
                } catch (IOException e) {
                    log.info("Unable to sendViaPut to url[" + url + "]", e);
                    throw AxisFault.makeFault(e);
                } finally {
                    cleanup(msgContext, response);
                }
            }
        };
    }
