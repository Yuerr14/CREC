                } else {
                    // no more shards active, add a failure
                    if (logger.isDebugEnabled()) {
                        if (t != null && !TransportActions.isShardNotAvailableException(t)) {
                            if (shard != null) {
                                logger.debug(shard.shortSummary() + ": Failed to execute [" + request + "]", t);
                            } else {
                                logger.debug(shardIt.shardId() + ": Failed to execute [" + request + "]", t);
                            }
                        }
                    }
                }
