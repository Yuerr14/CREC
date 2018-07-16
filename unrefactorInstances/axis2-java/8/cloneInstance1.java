    private void process() throws AxisFault {
        Object ob = configContext.getProperty(JsonConstant.XMLNODES);
        if (ob != null) {
            Map<QName, XmlNode> nodeMap = (Map<QName, XmlNode>) ob;
            XmlNode requesNode = nodeMap.get(elementQname);
            if (requesNode != null) {
                xmlNodeGenerator = new XmlNodeGenerator();
                queue = xmlNodeGenerator.getQueue(requesNode);
            } else {
                xmlNodeGenerator = new XmlNodeGenerator(xmlSchemaList, elementQname);
                mainXmlNode = xmlNodeGenerator.getMainXmlNode();
                queue = xmlNodeGenerator.getQueue(mainXmlNode);
                nodeMap.put(elementQname, mainXmlNode);
                configContext.setProperty(JsonConstant.XMLNODES, nodeMap);
            }
        } else {
            Map<QName, XmlNode> newNodeMap = new HashMap<QName, XmlNode>();
            xmlNodeGenerator = new XmlNodeGenerator(xmlSchemaList, elementQname);
            mainXmlNode = xmlNodeGenerator.getMainXmlNode();
            queue = xmlNodeGenerator.getQueue(mainXmlNode);
            newNodeMap.put(elementQname, mainXmlNode);
            configContext.setProperty(JsonConstant.XMLNODES, newNodeMap);
        }
        isProcessed = true;
    }
