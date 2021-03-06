    public void testLoadWSDLSEI() {
    	String sep = java.io.File.separator;
    	String wsdlRelativeLocation = ".." + sep + ".." + sep + "test-resources" + sep + "wsdl" + sep;
        String wsdlFileName = "EchoMessageService.wsdl";
        String wsdlLocation = wsdlRelativeLocation + wsdlFileName;

        // Build up a DBC, including the WSDL Definition and the annotation information for 
        // the impl class.
        JavaClassToDBCConverter converter = new JavaClassToDBCConverter(EchoMessageServiceSEI.class);
        HashMap<String, DescriptionBuilderComposite> dbcMap = converter.produceDBC();
        assertNotNull(dbcMap);
        DescriptionBuilderComposite dbc = dbcMap.get(EchoMessageServiceSEI.class.getName());
        assertNotNull(dbc);
        DescriptionBuilderComposite seiDBC = dbcMap.get(EchoMessageServiceInterface.class.getName());
        assertNotNull(seiDBC);
        dbc.setClassLoader(this.getClass().getClassLoader());
        assertNotNull(this.getClass().getClassLoader().getResource((wsdlLocation)));

        WebServiceAnnot webServiceAnnot = seiDBC.getWebServiceAnnot();
        assertNotNull(webServiceAnnot);
        webServiceAnnot.setWsdlLocation(wsdlLocation);
        seiDBC.setWebServiceAnnot(webServiceAnnot);
        dbcMap.put(EchoMessageServiceInterface.class.getName(), seiDBC);
        
        List<ServiceDescription> serviceDescList =
            DescriptionFactory.createServiceDescriptionFromDBCMap(dbcMap);
        assertEquals(1, serviceDescList.size());
        ServiceDescription sd = serviceDescList.get(0);
        assertNotNull(sd);
        
        // make sure the WSDL definition was read in from the appropriate location
        assertNotNull(((ServiceDescriptionWSDL) sd).getWSDLDefinition());
    }
