    public void testMethod3() {

        try {
            RMIClient rmiClient = new RMIClient(Service3.class, configurator, "http://localhost:8080/axis2/services/Service3");
            List inputObjects = new ArrayList();
            inputObjects.add(new Integer(5));
            rmiClient.invokeMethod("method3", inputObjects);
        } catch (Exception e) {
            if (e instanceof Exception3) {
                System.out.println("Got the exception 3");
            } else {
                e.printStackTrace();
            }
        }
    }
