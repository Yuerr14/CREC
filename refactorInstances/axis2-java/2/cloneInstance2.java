    public SOAPBody getBody() throws OMException {
        OMNode node = getFirstChild();
        while (node != null) {
            if ((node != null) && (node.getType() == OMNode.ELEMENT_NODE)) {
                OMElement element = (OMElement) node;
                if (OMConstants.BODY_LOCAL_NAME.equals(
                        element.getLocalName())) {
                    return (SOAPBody) element;
                }
            }
            node = node.getNextSibling();
        }
        return null;
    }
