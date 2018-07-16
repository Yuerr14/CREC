    public void testThreshold() {
        ServiceDescription sd = DescriptionFactory.createServiceDescription(ThresholdService.class);
        
        EndpointDescription ed = sd.getEndpointDescription(new QName(ns, thresholdServicePortName));
        assertTrue("The EndpointDescription should not be null.", ed != null);
        
        int threshold = ed.getMTOMThreshold();
        assertTrue("MTOM threshold should be 2000.", threshold == 20000);
    }
