            throws AxisFault {
        return new Request() {
            @Override
            public void execute() throws AxisFault {
                AbstractHttpClient httpClient = getHttpClient(msgContext);
        
                /*
                 * What's up with this, it never gets used anywhere?? --Glen String
                 * charEncoding = (String)
                 * msgContext.getProperty(Constants.Configuration
                 * .CHARACTER_SET_ENCODING);
                 *
                 * if (charEncoding == null) { charEncoding =
                 * MessageContext.DEFAULT_CHAR_SET_ENCODING; }
                 */
        
                HttpPost postMethod = new HttpPost();
                if (log.isTraceEnabled()) {
                    log.trace(Thread.currentThread() + " PostMethod " + postMethod + " / " + httpClient);
                }
                MessageFormatter messageFormatter = populateCommonProperties(msgContext, url, postMethod,
                                                                             httpClient, soapActionString);
                AxisRequestEntityImpl requestEntity =
                        new AxisRequestEntityImpl(messageFormatter, msgContext, format,
                                                  soapActionString, chunked, isAllowedRetry);
                postMethod.setEntity(requestEntity);
        
                if (!httpVersion.equals(HTTPConstants.HEADER_PROTOCOL_10) && chunked) {
                    requestEntity.setChunked(chunked);
                }
        
                String soapAction = messageFormatter.formatSOAPAction(msgContext, format, soapActionString);
        
                if (soapAction != null && !msgContext.isDoingREST()) {
                    postMethod.setHeader(HTTPConstants.HEADER_SOAP_ACTION, soapAction);
                }
        
                /*
                 * main execution takes place..
                 */
                HttpResponse response = null;
                try {
                    response = executeMethod(httpClient, msgContext, url, postMethod);
                    handleResponse(msgContext, response);
                } catch (IOException e) {
                    log.info("Unable to sendViaPost to url[" + url + "]", e);
                    throw AxisFault.makeFault(e);
                } finally {
                    cleanup(msgContext, response);
                }
            }
        };
    }
