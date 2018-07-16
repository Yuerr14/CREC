                    } else if (schemaType instanceof XmlSchemaSimpleType) {
                        //set a name
                        schemaType.setName(generatedTypeName.getLocalPart());
                        // Must do this up front to support recursive types
                        String fullyQualifiedClassName = writer.makeFullyQualifiedClassName(schemaType.getQName());
                        processedTypemap.put(schemaType.getQName(), fullyQualifiedClassName);

                        BeanWriterMetaInfoHolder metaInfHolder = (BeanWriterMetaInfoHolder) processedAnonymousComplexTypesMap.get(xsElt);
                        metaInfHolder.setOwnQname(schemaType.getQName());
                        metaInfHolder.setOwnClassName(fullyQualifiedClassName);

                        writeSimpleType((XmlSchemaSimpleType) schemaType,
                                metaInfHolder);
                        //remove the reference from the anon list since we named the type
                        processedAnonymousComplexTypesMap.remove(xsElt);
                        String className = findClassName(schemaType.getQName(), isArray(xsElt));
                        innerElementMap.put(
                                xsElt.getQName(),
                                className);

                        //store in the schema map
                        xsElt.addMetaInfo(
                                SchemaConstants.SchemaCompilerInfoHolder.CLASSNAME_KEY,
                                className);

                    }
