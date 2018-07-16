    public void testMethod2() {

        try {
            RMIClient rmiClient = new RMIClient(Service3.class, configurator, "http://localhost:8080/axis2/services/Service3");
            List inputObjects = new ArrayList();
            inputObjects.add("test string");
            rmiClient.invokeMethod("method2", inputObjects);
        } catch (Exception e) {
            if (e instanceof Exception2) {
                System.out.println("Got the exception 2");
            } else {
                e.printStackTrace();
            }
        }
    }
