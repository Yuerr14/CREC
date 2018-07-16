        if (!response.containsHeader("Connection")) {
            // See if the the client explicitly handles connection persistence
            Header connheader = request.getFirstHeader("Connection");
            if (connheader != null) {
                if (connheader.getValue().equalsIgnoreCase("keep-alive")) {
                    Header header = new Header("Connection", "keep-alive"); 
                    response.addHeader(header);
                    conn.setKeepAlive(true);
                }
                if (connheader.getValue().equalsIgnoreCase("close")) {
                    Header header = new Header("Connection", "close"); 
                    response.addHeader(header);
                    conn.setKeepAlive(false);
                }
            } else {
                // Use protocol default connection policy
                if (response.getHttpVersion().greaterEquals(HttpVersion.HTTP_1_1)) {
                    conn.setKeepAlive(true);
                } else {
                    conn.setKeepAlive(false);
                }
            }
        }
