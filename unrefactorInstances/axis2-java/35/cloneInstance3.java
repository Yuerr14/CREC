	public void testAsyncCallback_asyncMEP_WsdlFault() throws Exception{
		
		AsyncPort port = getPort();
		CallbackHandler<ThrowExceptionResponse> handler = new CallbackHandler<ThrowExceptionResponse>();
		Future<?> resp = port.throwExceptionAsync(ExceptionTypeEnum.WSDL_FAULT, handler);

		AsyncClient.waitBlocking(resp);
		
		try {
			handler.get();
			
			fail("ExecutionException expected at Response.get when ednpoint throws an exception");
		} catch (ExecutionException ee) {
			//Constants.logStack(ee);

			assertTrue(
					"ExecutionException.getCause should be an instance of SimpleFault",
					ee.getCause() instanceof ThrowExceptionFault);
		}
	}
