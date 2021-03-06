    public void testFaultsService1(){
        //FaultyWebServiceFault_Exception exception = null;
        Exception exception = null;
        try{
            System.out.println("----------------------------------");
            System.out.println("test: " + getName());
            FaultsService service = new FaultsService();
            FaultsServicePortType proxy = service.getFaultsPort();
            BindingProvider p = (BindingProvider)proxy;
            p.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,axisEndpoint);

            // the invoke will throw an exception, if the test is performed right
            int total = proxy.throwFault(2, "a", 2);
            
        }catch(BaseFault_Exception e){
            exception = e;
        } catch (ComplexFault_Exception e) {
            fail("Should not get ComplexFault_Exception in this testcase");
        }
        
        System.out.println("----------------------------------");
        
        assertNotNull(exception);
        assertTrue(((BaseFault_Exception)exception).getFaultInfo() instanceof DerivedFault2);
        
    }
