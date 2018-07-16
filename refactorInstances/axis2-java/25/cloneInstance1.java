                            if (annotation != null) {
                                Class claxx = Class.forName(
                                        "org.apache.axis2.jaxws.description.DescriptionFactory");
                                Method mthod = claxx.getMethod(
                                        "createAxisService",
                                        new Class[]{Class.class});
                                Class pojoClass = Loader.loadClass(classLoader, className);
                                AxisService axisService =
                                        (AxisService) mthod.invoke(claxx, new Object[]{pojoClass});
                                Utils.fillAxisService(axisService,
                                                      configCtx.getAxisConfiguration(),
                                                      new ArrayList(),
                                                      new ArrayList());
                                setMessageReceivers(axisService);
                                configCtx.getAxisConfiguration().addService(axisService);
                            } else {
