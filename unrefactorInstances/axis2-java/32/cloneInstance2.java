        } else {
            out = (OutputStream) msgContext.getProperty(MessageContext.TRANSPORT_OUT);

            if (out != null) {
                TransportUtils.writeMessage(msgContext, out);
            } else {
                throw new AxisFault(
                        "Both the TO and Property MessageContext.TRANSPORT_OUT is Null, No where to send");
            }
        }
