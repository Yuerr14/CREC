            if (jMethod.getExceptionTypes().length > 0) {
                JClass[] extypes = jMethod.getExceptionTypes();
                for (int j = 0; j < extypes.length; j++) {
                    JClass extype = extypes[j];
                    if (AxisFault.class.getName().equals(extype.getQualifiedName())) {
                        continue;
                    }
                    if (!generateBaseException) {
                        methodSchemaType = createSchemaTypeForMethodPart("Exception");
                        sequence = new XmlSchemaSequence();
                        QName schemaTypeName = typeTable.getSimpleSchemaTypeName(Exception.class.getName());
                        addContentToMethodSchemaType(sequence,
                                schemaTypeName,
                                "Exception",
                                false);
                        methodSchemaType.setParticle(sequence);
                        generateBaseException = true;
                    }
                    String partQname = extype.getSimpleName();
                    methodSchemaType = createSchemaTypeForMethodPart(partQname);
                    sequence = new XmlSchemaSequence();
                    if (Exception.class.getName().equals(extype.getQualifiedName())) {
                        addContentToMethodSchemaType(sequence,
                                typeTable.getComplexSchemaType("Exception"),
                                partQname,
                                false);
                        methodSchemaType.setParticle(sequence);
                        typeTable.addComplexSchema(Exception.class.getPackage().getName(),
                                methodSchemaType.getQName());
                    } else {
                        generateSchemaForType(sequence, extype, extype.getSimpleName());
                        methodSchemaType.setParticle(sequence);
                    }
                    if (AxisFault.class.getName().equals(extype.getQualifiedName())) {
                        continue;
                    }
                    AxisMessage faultMessage = new AxisMessage();
                    faultMessage.setName(extype.getSimpleName());
                    faultMessage.setElementQName(typeTable.getQNamefortheType(partQname));
                    axisOperation.setFaultMessages(faultMessage);
                }
            }
