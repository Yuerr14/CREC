            throws AxisFault {
        HashMap map = new HashMap();
        try {

            StringBuffer str = new StringBuffer();

            int state = BEFORE_SEPERATOR;

            String key = null;
            String value = null;

            int start = 0;

            length = readLine(reader, buf);
            if (serverSide) {
                if (buf[0] == 'P'
                        && buf[1] == 'O'
                        && buf[2] == 'S'
                        && buf[3] == 'T') {
                    index = 5;
                    value = readFirstLineArg(' ');
                    map.put(HTTPConstants.REQUEST_URI, value);
                    value = readFirstLineArg('\n');
                    map.put(HTTPConstants.PROTOCOL_VERSION, value);
                } else {
                    throw new AxisFault("Only the POST requests are supported");
                }
            } else {
                index = 0;
                value = readFirstLineArg(' ');
                map.put(HTTPConstants.PROTOCOL_VERSION, value);
                value = readFirstLineArg(' ');
                map.put(HTTPConstants.RESPONSE_CODE, value);
                value = readFirstLineArg('\n');
                map.put(HTTPConstants.RESPONSE_WORD, value);
            }

            state = BEFORE_SEPERATOR;


            while (!done) {
                length = readLine(reader, buf);
                if (length <= 0) {
                    throw new AxisFault("Premature end of steam");
                }
                for (int i = 0; i < length; i++) {
                    switch (state) {
                        case BEFORE_SEPERATOR:
                            if (buf[i] == ':') {
                                key = str.toString();
                                str = new StringBuffer();
                                state = AFTER_SEPERATOR;

                                if (buf[i + 1] == ' ') {
                                    i++; //ignore next space
                                }
                            } else {
                                str.append(buf[i]);
                            }
                            break;
                        case AFTER_SEPERATOR:
                            if (buf[i] == '\n') {
                                value = str.toString();
                                map.put(key, value);
                                str = new StringBuffer();
                                i = length;
                            } else {
                                str.append(buf[i]);
                            }
                            break;
                            //                            case END_OF_LINE :
                            //                                if (buf[i] == '\n') {
                            //                                    state = END;
                            //                                    break;
                            //                                } else {
                            //                                    state = BEFORE_SEPERATOR;
                            //                                    str.append(buf[i]);
                            //                                }
                            //                                break;
                            //                            case END:
                            //                            break;    
                        default :
                            throw new AxisFault("Error Occured Unknown state " + state);

                    }
                }

                state = BEFORE_SEPERATOR;

            }
        } catch (IOException e) {
            throw new AxisFault(e.getMessage(), e);
        }
        return map;
    }
