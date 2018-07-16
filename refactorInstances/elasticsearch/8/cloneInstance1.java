                        for (MappingMetaData mappingMd : indexMetaData.mappings().values()) {
                            if (!types.isEmpty() && !types.contains(mappingMd.type())) {
                                // filter this type out...
                                continue;
                            }
                            foundType = true;
                            byte[] mappingSource = mappingMd.source().uncompressed();
                            XContentParser parser = XContentFactory.xContent(mappingSource).createParser(mappingSource);
                            Map<String, Object> mapping = parser.map();
                            if (mapping.size() == 1 && mapping.containsKey(mappingMd.type())) {
                                // the type name is the root value, reduce it
                                mapping = (Map<String, Object>) mapping.get(mappingMd.type());
                            }
                            builder.field(mappingMd.type());
                            builder.map(mapping);
                        }
