        public void collect(int doc, long owningBucketOrdinal) throws IOException {
            assert owningBucketOrdinal == 0 : "this is a per_bucket aggregator";
            final int valuesCount = ordinals.setDocument(doc);

            for (int i = 0; i < valuesCount; ++i) {
                final long ord = ordinals.nextOrd();
                long bucketOrd = ordinalToBucket.get(ord);
                if (bucketOrd < 0) { // unlikely condition on a low-cardinality field
                    final BytesRef bytes = bytesValues.getValueByOrd(ord);
                    final int hash = bytesValues.currentValueHash();
                    assert hash == bytes.hashCode();
                    bucketOrd = bucketOrds.add(bytes, hash);
                    if (bucketOrd < 0) { // already seen in another segment
                        bucketOrd = - 1 - bucketOrd;
                    }
                    ordinalToBucket.set(ord, bucketOrd);
                }

                collectBucket(doc, bucketOrd);
            }
        }
