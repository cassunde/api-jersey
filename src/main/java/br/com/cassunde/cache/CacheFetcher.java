package br.com.cassunde.cache;

import org.infinispan.Cache;

import br.com.cassunde.model.CacheID;

public class CacheFetcher<K, E extends CacheID> {

    Cache<K, E> fetchCache(final String cacheName) {
        return CacheSingleton.instance.getCacheManager().getCache(cacheName);
    }
}
