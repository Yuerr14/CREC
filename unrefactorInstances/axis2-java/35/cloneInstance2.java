	public void testAsyncPolling_asyncMEP_WsdlFault() throws Exception{
		
		AsyncPort port = getPort();
		Response<ThrowExceptionResponse> resp = port
				.throwExceptionAsync(ExceptionTypeEnum.WSDL_FAULT);

		AsyncClient.waitBlocking(resp);
		try {
			resp.get();
			fail("ExecutionException expected at Response.get when ednpoint throws an exception");
		} catch (ExecutionException ee) {
			//Constants.logStack(ee);

			assertTrue(
					"ExecutionException.getCause should be an instance of SimpleFault",
					ee.getCause() instanceof ThrowExceptionFault);
		}
	}
