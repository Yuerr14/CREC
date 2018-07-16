    @Override public void clear(IndexReader reader) {
        // we add the seen reader before we add the first cache entry for this reader
        // so, if we don't see it here, its won't be in the cache
        Boolean removed = seenReaders.remove(reader.getCoreCacheKey());
        if (removed == null) {
            return;
        }
        seenReadersCount.decrementAndGet();
        ConcurrentMap<FilterCacheKey, FilterCacheValue<DocSet>> cache = cache();
        for (FilterCacheKey key : cache.keySet()) {
            if (key.readerKey() == reader.getCoreCacheKey()) {
                FilterCacheValue<DocSet> removed2 = cache.remove(key);
                if (removed2 != null) {
                    totalCount.decrementAndGet();
                    totalSizeInBytes.addAndGet(-removed2.value().sizeInBytes());
                }
            }
        }
    }
