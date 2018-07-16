                            for (int j = 0; j < i1; j++) {
                                Object o = Array.get(value, j);
                                if (elemntNameSpace != null) {
                                    object.add(new QName(elemntNameSpace.getNamespaceURI(),
                                                         propDesc.getName(),
                                                         elemntNameSpace.getPrefix()));
                                } else {
                                    object.add(new QName(beanName.getNamespaceURI(),
                                                         propDesc.getName(), beanName.getPrefix()));
                                }
                                object.add(o == null ? null : SimpleTypeMapper.getStringValue(o));
                            }
