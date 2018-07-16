    public void testBadThreshold() {
        ServiceDescription sd = DescriptionFactory.createServiceDescription(BadThresholdService.class);
        
        EndpointDescription ed = sd.getEndpointDescription(new QName(ns, badThresholdServicePortName));
        assertTrue("The EndpointDescription should not be null.", ed != null);
        
        int threshold = ed.getMTOMThreshold();
        assertTrue("MTOM threshold should be [0], but was [" + threshold + "].", threshold == 0);
    }
