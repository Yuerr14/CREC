                } else {
                    if (elemntNameSpace != null) {
                        object.add(new QName(elemntNameSpace.getNamespaceURI(),
                                             propDesc.getName(), elemntNameSpace.getPrefix()));
                    } else {
                        object.add(new QName(beanName.getNamespaceURI(),
                                             propDesc.getName(), beanName.getPrefix()));
                    }
                    Object value = propDesc.getReadMethod().invoke(beanObject,
                                                                   null);
                    object.add(value);
                }
