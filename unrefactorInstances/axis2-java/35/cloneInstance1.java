	public void testAsyncPolling_asyncMEP_WebServiceException() throws Exception {
		
		AsyncPort port = getPort();
		Response<ThrowExceptionResponse> resp = port
				.throwExceptionAsync(ExceptionTypeEnum.WSE);

		AsyncClient.waitBlocking(resp);
		try {
			resp.get();
			fail("ExecutionException expected at Response.get when ednpoint throws an exception");
		} catch (ExecutionException ee) {
			//Constants.logStack(ee);

			assertTrue(
					"ExecutionException.getCause should be an instance of SOAPFaultException",
					ee.getCause() instanceof SOAPFaultException);
		}
	}
