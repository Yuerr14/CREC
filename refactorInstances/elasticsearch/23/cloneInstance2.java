    private AttributesRoutings getInitializingAttribute(AttributesKey key, DiscoveryNodes nodes) {
        AttributesRoutings shardRoutings = initializingShardsByAttributes.get(key);
        if (shardRoutings == null) {
            synchronized (shardsByAttributeMutex) {
                ArrayList<ShardRouting> from = new ArrayList<ShardRouting>(allInitializingShards);
                ArrayList<ShardRouting> to = new ArrayList<ShardRouting>();
                for (String attribute : key.attributes) {
                    String localAttributeValue = nodes.localNode().attributes().get(attribute);
                    if (localAttributeValue == null) {
                        continue;
                    }
                    for (Iterator<ShardRouting> iterator = from.iterator(); iterator.hasNext(); ) {
                        ShardRouting fromShard = iterator.next();
                        if (localAttributeValue.equals(nodes.get(fromShard.currentNodeId()).attributes().get(attribute))) {
                            iterator.remove();
                            to.add(fromShard);
                        }
                    }
                }

                shardRoutings = new AttributesRoutings(ImmutableList.copyOf(to), ImmutableList.copyOf(from));
                initializingShardsByAttributes = MapBuilder.newMapBuilder(initializingShardsByAttributes).put(key, shardRoutings).immutableMap();
            }
        }
        return shardRoutings;
    }
