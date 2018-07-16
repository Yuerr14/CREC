    public boolean ensureCapacityInt(DynamicObject array, int requiredCapacity) {
        final int[] store = (int[]) Layouts.ARRAY.getStore(array);

        if (allocateProfile.profile(store.length < requiredCapacity)) {
            Layouts.ARRAY.setStore(array, Arrays.copyOf(store, ArrayUtils.capacity(getContext(), store.length, requiredCapacity)));
            Layouts.ARRAY.setSize(array, Layouts.ARRAY.getSize(array));
            return true;
        } else {
            return false;
        }
    }
