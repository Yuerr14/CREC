                    {
                        int formatOpt = TclIndex.get(interp, objv[argIx],
                                formatOpts, "switch", 0);
                        switch (formatOpt)
                        {
                            case OPT_FORMAT_FORMAT : {
                                format = objv[argIx + 1].toString();
                                break;
                            }
                            case OPT_FORMAT_GMT : {
                                useGmt = TclBoolean
                                        .get(interp, objv[argIx + 1]);
                                break;
                            }
                        }
                    }
