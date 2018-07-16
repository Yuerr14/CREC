    private void process() throws IOException {
        Object ob = configContext.getProperty(JsonConstant.XMLNODES);
        if (ob != null) {
            Map<QName, XmlNode> nodeMap = (Map<QName, XmlNode>) ob;
            XmlNode resNode = nodeMap.get(elementQName);
            if (resNode != null) {
                xmlNodeGenerator = new XmlNodeGenerator();
                queue = xmlNodeGenerator.getQueue(resNode);
            } else {
                xmlNodeGenerator = new XmlNodeGenerator(xmlSchemaList, elementQName);
                mainXmlNode = xmlNodeGenerator.getMainXmlNode();
                queue = xmlNodeGenerator.getQueue(mainXmlNode);
                nodeMap.put(elementQName, mainXmlNode);
                configContext.setProperty(JsonConstant.XMLNODES, nodeMap);
            }
        } else {
            Map<QName, XmlNode> newNodeMap = new HashMap<QName, XmlNode>();
            xmlNodeGenerator = new XmlNodeGenerator(xmlSchemaList, elementQName);
            mainXmlNode = xmlNodeGenerator.getMainXmlNode();
            queue = xmlNodeGenerator.getQueue(mainXmlNode);
            newNodeMap.put(elementQName, mainXmlNode);
            configContext.setProperty(JsonConstant.XMLNODES, newNodeMap);
        }
        isProcessed = true;
        this.jsonWriter.beginObject();
    }
