        for (XmlSchema xmlSchema : schemaColl) {
            i++;
            // /XmlSchema schema = schemaColl.iterator().next();
            byteArrayOutputStream = new ByteArrayOutputStream();
            xmlSchema.write(byteArrayOutputStream);
            String XML1 = byteArrayOutputStream.toString();

            // Enum has differences when generating schema files
            if (XML1.contains("\"http://ws.apache.org/namespaces/axis2/enum\"")) {
                XML1 = prepareForEnum(XML1);
            }
            XML1 = prepareForEnum(XML1);
            String XML2 = readSchema(c, i);
            assertSimilarXML(XML1, XML2);
            assertIdenticalXML(XML1, XML2);
        }
