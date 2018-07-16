        for (int i = 0; i < loopCount; ++i) {
            Source payload = msg.getPayload();
            assertTrue("Attempt number "  + i + " to get the payload (Source) was null", payload != null);


            String resultContent = _getStringFromSource(payload);
            assertTrue("The content returned in loop " + i + " was null", resultContent != null);
            assertTrue("The content returned in loop " + i + " was incomplete, unexpected element", resultContent.indexOf("echoString") > -1);
            assertTrue("The content returned in loop " + i + " was incomplete, unexpected content", resultContent.indexOf(INPUT) > -1);            
        }
