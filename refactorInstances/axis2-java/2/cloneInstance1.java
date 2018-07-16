    public SOAPHeader getHeader() throws OMException {

        // if(builder != null){
        // while(header == null && body == null){
        // builder.next();
        // }
        // }
        OMNode node = getFirstChild();
        while (node != null) {
            if ((node != null) && (node.getType() == OMNode.ELEMENT_NODE)) {
                OMElement element = (OMElement) node;
                if (OMConstants.HEADER_LOCAL_NAME.equals(
                        element.getLocalName())) {
                    return (SOAPHeader) element;
                }
            }
            node = node.getNextSibling();
        }
        return null;
    }
