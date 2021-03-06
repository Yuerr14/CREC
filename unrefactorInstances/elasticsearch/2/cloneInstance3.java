    public static <T> Set<Provider<T>> getProvidersOf(Injector injector, Matcher<Class> matcher) {
        Set<Provider<T>> answer = Sets.newHashSet();
        Set<Entry<Key<?>, Binding<?>>> entries = injector.getBindings().entrySet();
        for (Entry<Key<?>, Binding<?>> entry : entries) {
            Key<?> key = entry.getKey();
            Class<?> keyType = getKeyType(key);
            if (keyType != null && matcher.matches(keyType)) {
                Binding<?> binding = entry.getValue();
                answer.add((Provider<T>) binding.getProvider());
            }
        }
        return answer;
    }
