            } else {
                BoundedTreeSet<InternalLongTermsFacet.LongEntry> ordered = new BoundedTreeSet<InternalLongTermsFacet.LongEntry>(comparatorType.comparator(), shardSize);
                for (int i = 0; i < states.length; i++) {
                    if (states[i]) {
                        ordered.add(new InternalLongTermsFacet.LongEntry(keys[i], values[i]));
                    }
                }
                facets.release();
                return new InternalLongTermsFacet(facetName, comparatorType, size, ordered, missing, total);
            }
