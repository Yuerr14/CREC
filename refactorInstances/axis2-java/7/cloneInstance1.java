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
