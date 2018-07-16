        for (int i = 0; i < 10; i++) {
            
            request1 = req1base + "_" + i;
            request2 = req2base + "_" + i;

            TestLogger.logger.debug(title + "iteration [" + i + "] using request1 [" + request1 +
                    "]  request2 [" + request2 + "]");

            // submit request #1 to the server-side web service that 
            // the web service will keep until we ask for it
            Response<SleepResponse> resp1 = port.sleepAsync(request1);

            // submit request #2 to the server that essentially processes
            // without delay
            Response<CustomAsyncResponse> resp2 = port.remappedAsync(request2);

            // wait until the response for request #2 is done 
            waitBlocking(resp2);

            // check the waiting request #1
            String asleep = port.isAsleep(request1);
            //System.out.println(title+"iteration ["+i+"]   port.isAsleep(request1 ["+request1+"]) = ["+asleep+"]");

            // wakeup the waiting request #1
            String wake = port.wakeUp(request1);
            //System.out.println(title+"iteration ["+i+"]   port.wakeUp(request1 ["+request1+"]) = ["+wake+"]");

            // wait until the response for request #1 is done
            waitBlocking(resp1);
        
            // get the responses
            String req1_result = null;
            String req2_result = null;

            try {
                req1_result = resp1.get().getMessage();
                req2_result = resp2.get().getResponse();
            } catch (Exception e) {
                TestLogger.logger.debug(
                        title + "iteration [" + i + "] using request1 [" + request1 +
                                "]  request2 [" + request2 + "] :  got exception [" +
                                e.getClass().getName() + "]  [" + e.getMessage() + "] ");
                e.printStackTrace();
                fail(e.toString());
            }

            // check status on request #1
            assertEquals("sleepAsync did not sleep as expected", request1, asleep);
            assertEquals("sleepAsync did not return expected response ", request1, req1_result);

            // check status on request #2
            assertEquals("remappedAsync did not return expected response", request2, req2_result);
            

            // Calling get() again should return the same object as the first call to get()
            assertEquals("sleepAsync did not return expected response ", request1, resp1.get().getMessage());
            assertEquals("remappedAsync did not return expected response", request2, resp2.get().getResponse());
            
        }
