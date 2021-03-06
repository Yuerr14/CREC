    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        // Wait for the response to come back
        latch.await(timeout, unit);
        
        if (hasFault()) {
            throw new ExecutionException(ExceptionFactory.makeWebServiceException(fault));
        }
        if (response == null) {
            throw new ExecutionException(ExceptionFactory.makeWebServiceException("null response"));
        }
        
        // TODO: Check the type of the object to make sure it corresponds with
        // the parameterized generic type.
        if (responseObj == null) {
            if (log.isDebugEnabled()) {
                log.debug("Demarshalling the async response message");
            }
            responseObj = getResponseValueObject(response);
        }

        return responseObj;
    }
