    protected void setUp() throws Exception {
        AxisGlobal global = new AxisGlobal();
        engineRegistry = new org.apache.axis.impl.engine.EngineRegistryImpl(global);
        
        AxisService service = new AxisService(serviceName);
        service.setClassLoader(Thread.currentThread().getContextClassLoader());
        service.setServiceClass(Echo.class);
        service.setProvider(new SimpleJavaProvider());

        AxisOperation operation1 = new SimpleAxisOperationImpl(operationName1);
        service.addOperation(operation1);
        
        AxisOperation operation2 = new SimpleAxisOperationImpl(operationName2);
        service.addOperation(operation2);

        EngineUtils.createExecutionChains(service);
        engineRegistry.addService(service);
        
        sas = EngineUtils.startServer(engineRegistry);
    }
