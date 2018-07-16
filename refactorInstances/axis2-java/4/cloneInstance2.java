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
                        metadata.getRules().setPhaseName("service");
                    }
                    allHandlers.add(metadata);
                }
            }
