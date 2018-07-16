        public DynamicObject mergePackedBuckets(VirtualFrame frame, DynamicObject hash, DynamicObject other, NotProvided block) {
            final boolean isCompareByIdentity = Layouts.HASH.getCompareByIdentity(hash);

            final DynamicObject merged = allocateObjectNode.allocateHash(Layouts.BASIC_OBJECT.getLogicalClass(hash), new Entry[BucketsStrategy.capacityGreaterThan(Layouts.HASH.getSize(hash) + Layouts.HASH.getSize(other))], 0, null, null, null, null, false);

            final Object[] hashStore = (Object[]) Layouts.HASH.getStore(hash);
            final int hashSize = Layouts.HASH.getSize(hash);

            for (int n = 0; n < getContext().getOptions().HASH_PACKED_ARRAY_MAX; n++) {
                if (n < hashSize) {
                    setNode.executeSet(frame, merged, PackedArrayStrategy.getKey(hashStore, n), PackedArrayStrategy.getValue(hashStore, n), isCompareByIdentity);
                }
            }

            for (KeyValue keyValue : BucketsStrategy.iterableKeyValues(Layouts.HASH.getFirstInSequence(other))) {
                setNode.executeSet(frame, merged, keyValue.getKey(), keyValue.getValue(), isCompareByIdentity);
            }

            assert HashOperations.verifyStore(getContext(), hash);

            return merged;
        }
