            if (module != null) {
                switch (flowtype) {
                    case PhaseMetadata.IN_FLOW:
                        {
                            flow = module.getInFlow();
                            break;
                        }
                    case PhaseMetadata.OUT_FLOW:
                        {
                            flow = module.getOutFlow();
                            break;
                        }
                    case PhaseMetadata.FAULT_IN_FLOW:
                        {
                            flow = module.getFaultInFlow();
                            break;
                        }
                    case PhaseMetadata.FAULT_OUT_FLOW:
                        {
                            flow = module.getFaultOutFlow();
                            break;
                        }
                }
                if (flow != null) {
                    for (int j = 0; j < flow.getHandlerCount(); j++) {
                        HandlerMetadata metadata = flow.getHandler(j);
                        /**
                         * If the phase property of a handler is pre-dispatch then those handlers
                         * should go to the global chain , to the pre-dispatch phase
                         */
                        if (PhaseMetadata.PRE_DISPATCH.equals(metadata.getRules().getPhaseName())) {
                            continue;
                        }
                        if (metadata.getRules().getPhaseName().equals("")) {
                            metadata.getRules().setPhaseName("global");
                        }
                        allHandlers.add(metadata);
                    }
                }
            } else {
