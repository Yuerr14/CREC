    public static <T> Set<Provider<T>> getProvidersOf(Injector injector, Class<T> baseClass) {
        Set<Provider<T>> answer = Sets.newHashSet();
        Set<Entry<Key<?>, Binding<?>>> entries = injector.getBindings().entrySet();
        for (Entry<Key<?>, Binding<?>> entry : entries) {
            Key<?> key = entry.getKey();
            Class<?> keyType = getKeyType(key);
            if (keyType != null && baseClass.isAssignableFrom(keyType)) {
                Binding<?> binding = entry.getValue();
                answer.add((Provider<T>) binding.getProvider());
            }
        }
        return answer;
    }
