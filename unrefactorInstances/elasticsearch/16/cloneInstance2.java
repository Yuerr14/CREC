            } else {
                BoundedTreeSet<InternalDoubleTermsFacet.DoubleEntry> ordered = new BoundedTreeSet<InternalDoubleTermsFacet.DoubleEntry>(comparatorType.comparator(), shardSize);
                for (int i = 0; i < states.length; i++) {
                    if (states[i]) {
                        ordered.add(new InternalDoubleTermsFacet.DoubleEntry(keys[i], values[i]));
                    }
                }
                facets.release();
                return new InternalDoubleTermsFacet(facetName, comparatorType, size, ordered, missing, total);
            }
