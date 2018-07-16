                if (xmlSchema.getTypeByName(complexTypeName) == null) {
                    xmlSchemaComplexType = new XmlSchemaComplexType(xmlSchema);
                    XmlSchemaSequence xmlSchemaSequence = new XmlSchemaSequence();
                    xmlSchemaComplexType.setParticle(xmlSchemaSequence);
                    xmlSchemaComplexType.setName(complexTypeName);

                    xmlSchema.getItems().add(xmlSchemaComplexType);
                    xmlSchema.getSchemaTypes().add(
                            new QName(schemaTargetNameSpace, xmlSchemaComplexType.getName()),
                            xmlSchemaComplexType);
                    addElementToSequence("array",
                            typeTable.getSimpleSchemaTypeName(propertyName),
                            xmlSchemaSequence,
                            propertyName.equals("base64Binary"),
                            isArrayType,
                            type.isPrimitive());
                } else {
