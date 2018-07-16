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
