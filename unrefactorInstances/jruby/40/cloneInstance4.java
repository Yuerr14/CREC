    public boolean ensureCapacityObject(DynamicObject array, int requiredCapacity) {
        final Object[] store = (Object[]) Layouts.ARRAY.getStore(array);

        if (allocateProfile.profile(store.length < requiredCapacity)) {
            Layouts.ARRAY.setStore(array, ArrayUtils.grow(store, ArrayUtils.capacity(getContext(), store.length, requiredCapacity)));
            Layouts.ARRAY.setSize(array, Layouts.ARRAY.getSize(array));
            return true;
        } else {
            return false;
        }
    }
