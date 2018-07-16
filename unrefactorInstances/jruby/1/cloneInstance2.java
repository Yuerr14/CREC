        public DynamicObject mergeBucketsPacked(VirtualFrame frame, DynamicObject hash, DynamicObject other, NotProvided block) {
            final boolean isCompareByIdentity = Layouts.HASH.getCompareByIdentity(hash);

            final DynamicObject merged = allocateObjectNode.allocateHash(Layouts.BASIC_OBJECT.getLogicalClass(hash), new Entry[BucketsStrategy.capacityGreaterThan(Layouts.HASH.getSize(hash) + Layouts.HASH.getSize(other))], 0, null, null, null, null, false);

            for (KeyValue keyValue : BucketsStrategy.iterableKeyValues(Layouts.HASH.getFirstInSequence(hash))) {
                setNode.executeSet(frame, merged, keyValue.getKey(), keyValue.getValue(), isCompareByIdentity);
            }

            final Object[] otherStore = (Object[]) Layouts.HASH.getStore(other);
            final int otherSize = Layouts.HASH.getSize(other);

            for (int n = 0; n < getContext().getOptions().HASH_PACKED_ARRAY_MAX; n++) {
                if (n < otherSize) {
                    setNode.executeSet(frame, merged, PackedArrayStrategy.getKey(otherStore, n), PackedArrayStrategy.getValue(otherStore, n), isCompareByIdentity);
                }
            }

            assert HashOperations.verifyStore(getContext(), hash);

            return merged;
        }
