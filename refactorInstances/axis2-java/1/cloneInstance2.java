    protected void setUp() throws Exception {
        Global global = new SimpleGlobal();
        global.setInFlow(new MockFlow("globel inflow",4));
        global.setOutFlow(new MockFlow("globel outflow",2));
        global.setFaultFlow(new MockFlow("globel faultflow",1));
        engineRegistry = new SimpleEngineRegistry(global);
        
        Transport transport = new SimpleTransport(transportName);
        transport.setInFlow(new MockFlow("transport inflow",4));
        transport.setOutFlow(new MockFlow("transport outflow",2));
        transport.setFaultFlow(new MockFlow("transport faultflow",1));
        engineRegistry.addTransport(transport);
        
        Service service = new SimpleService(serviceName);
        service.setInFlow(new MockFlow("service inflow",4));
        service.setOutFlow(new MockFlow("service outflow",5));
        service.setFaultFlow(new MockFlow("service faultflow",1));
        service.setClassLoader(Thread.currentThread().getContextClassLoader());
        
        Parameter classParam = new ConcreateParameter("className",EchoService.class.getName());
        service.addParameter(classParam);
         
        service.setProvider(new SimpleJavaProvider());
        
        Module m1 = new SimpleModule(new QName("","A Mdoule 1"));
        m1.setInFlow(new MockFlow("service module inflow",4));
        m1.setFaultFlow(new MockFlow("service module faultflow",1));
        service.addModule(m1);
        
        Operation operation = new SimpleOperation(operationName,service);
        operation.setInFlow(new MockFlow("inflow",4));
        
        service.addOperation(operation);
        engineRegistry.addService(service);
        
        mc = new MessageContext(engineRegistry);
        mc.setCurrentTansport(transportName);
        mc.setCurrentService(serviceName);
        mc.setCurrentOperation(operationName);
        
        AxisEngine engine = new AxisEngine(engineRegistry);
        sas = new SimpleAxisServer(engine);
        sas.setServerSocket(new ServerSocket(testingPort));
        thisThread = new Thread(sas);
        thisThread.start();
    }
