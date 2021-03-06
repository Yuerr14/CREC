    public void testLoadWSDLImpl() {
    	String sep = java.io.File.separator;
    	String wsdlRelativeLocation = ".." + sep + ".." + sep + "test-resources" + sep + "wsdl" + sep;
        String wsdlFileName = "EchoMessageService.wsdl";
        String wsdlLocation = wsdlRelativeLocation + wsdlFileName;

        // Build up a DBC, including the WSDL Definition and the annotation information for 
        // the impl class.
        JavaClassToDBCConverter converter = new JavaClassToDBCConverter(EchoMessageService.class);
        HashMap<String, DescriptionBuilderComposite> dbcMap = converter.produceDBC();
        assertNotNull(dbcMap);
        DescriptionBuilderComposite dbc = dbcMap.get(EchoMessageService.class.getName());
        assertNotNull(dbc);
        dbc.setClassLoader(this.getClass().getClassLoader());
        assertNotNull(this.getClass().getClassLoader().getResource((wsdlLocation)));

        WebServiceAnnot webServiceAnnot = dbc.getWebServiceAnnot();
        assertNotNull(webServiceAnnot);
        webServiceAnnot.setWsdlLocation(wsdlLocation);
        dbc.setWebServiceAnnot(webServiceAnnot);
        dbcMap.put(EchoMessageService.class.getName(), dbc);
        
        List<ServiceDescription> serviceDescList =
            DescriptionFactory.createServiceDescriptionFromDBCMap(dbcMap);
        assertEquals(1, serviceDescList.size());
        ServiceDescription sd = serviceDescList.get(0);
        assertNotNull(sd);
        
        // make sure the WSDL definition was read in from the appropriate location
        assertNotNull(((ServiceDescriptionWSDL) sd).getWSDLDefinition());
    }
