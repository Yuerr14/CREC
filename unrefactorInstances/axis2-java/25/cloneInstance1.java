            throws AxisFault {
        return new Request() {
            @Override
            public void execute() throws AxisFault {
                HttpClient httpClient = getHttpClient(msgContext);
        
                /*
                 * What's up with this, it never gets used anywhere?? --Glen String
                 * charEncoding = (String)
                 * msgContext.getProperty(Constants.Configuration
                 * .CHARACTER_SET_ENCODING);
                 * 
                 * if (charEncoding == null) { charEncoding =
                 * MessageContext.DEFAULT_CHAR_SET_ENCODING; }
                 */
        
                PostMethod postMethod = new PostMethod();
                if (log.isTraceEnabled()) {
                    log.trace(Thread.currentThread() + " PostMethod " + postMethod + " / " + httpClient);
                }
                MessageFormatter messageFormatter = populateCommonProperties(msgContext, url, postMethod,
                        httpClient, soapActionString);
        
                postMethod.setRequestEntity(new AxisRequestEntityImpl(messageFormatter, msgContext, format,
                        soapActionString, chunked, isAllowedRetry));
        
                if (!httpVersion.equals(HTTPConstants.HEADER_PROTOCOL_10) && chunked) {
                    postMethod.setContentChunked(true);
                }
        
                String soapAction = messageFormatter.formatSOAPAction(msgContext, format, soapActionString);
        
                if (soapAction != null && !msgContext.isDoingREST()) {
                    postMethod.setRequestHeader(HTTPConstants.HEADER_SOAP_ACTION, soapAction);
                }
        
                /*
                 * main excecution takes place..
                 */
                try {
                    executeMethod(httpClient, msgContext, url, postMethod);
                    handleResponse(msgContext, postMethod);
                } catch (IOException e) {
                    log.info("Unable to sendViaPost to url[" + url + "]", e);
                    throw AxisFault.makeFault(e);
                } finally {
                    cleanup(msgContext, postMethod);
                }
            }
        };
    }
