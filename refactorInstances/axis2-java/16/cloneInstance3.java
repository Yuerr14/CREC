    public void testechoBean2() throws AxisFault {
        configureSystem("echoBean");

        Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        ConfigurationContext configContext =
                ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
        RPCServiceClient sender = new RPCServiceClient(configContext, null);
        sender.setOptions(options);

        MyBean bean = new MyBean();
        bean.setAge(100);
        bean.setName("Deepal");
        bean.setValue(false);
        AddressBean ab = new AddressBean();
        ab.setNumber(1010);
        ab.setTown("Colombo3");
        bean.setAddress(ab);

        ArrayList args = new ArrayList();
        args.add(bean);

        ArrayList ret = new ArrayList();
        ret.add(MyBean.class);

        Object [] response = sender.invokeBlocking(operationName, args.toArray(),
                                                   (Class[])ret.toArray(new Class[ret.size()]));
        MyBean resBean = (MyBean)response[0];
        assertNotNull(resBean);
        assertEquals(resBean.getAge(), 100);
    }
