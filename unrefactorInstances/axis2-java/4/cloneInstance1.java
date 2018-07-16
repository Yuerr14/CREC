    public void testMethod1() {

        try {
            RMIClient rmiClient = new RMIClient(Service3.class, configurator, "http://localhost:8080/axis2/services/Service3");
            List inputObjects = new ArrayList();
            rmiClient.invokeMethod("method1", inputObjects);
        } catch (Exception e) {
            if (e instanceof Exception1) {
                System.out.println("Got the exception 1");
            } else {
                e.printStackTrace();
            }
        }
    }
