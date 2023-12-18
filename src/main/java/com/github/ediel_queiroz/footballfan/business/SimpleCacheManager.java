package com.github.ediel_queiroz.footballfan.business;

import java.util.Map;
import java.util.Objects;

public class SimpleCacheManager<K,V> {

    private final Map<K, V> cacheMap;

    public SimpleCacheManager(final Map<K, V> cacheMap) {
        this.cacheMap = cacheMap;
    }

    public void put(K key, V value) {
        Objects.requireNonNull(key, "Key is required");
        Objects.requireNonNull(value, "Value is required");
        cacheMap.put(key, value);
    }

    public V get(K id) {
        return cacheMap.get(id);
    }
}
