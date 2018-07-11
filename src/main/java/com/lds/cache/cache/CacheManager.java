package com.lds.cache.cache;


import com.lds.cache.cache.impl.LruCache;
import com.lds.cache.cache.impl.LruCacheQueen;

import java.io.IOException;
import java.util.Properties;

/**
 * Title: CacheManager
 * <p>
 * Description:
 * </p>
 *
 * @author liudongshun
 * @version V1.0
 * @since 2018/07/11
 */
public class CacheManager {

    private static volatile LruCacheQueen singleton;

    private static volatile LruCache cache;

    public static LruCacheQueen getQueenCache() {
        if (singleton == null) {
            synchronized (CacheManager.class) {
                if (singleton == null) {
                    int cacheSize ;
                    Properties prop = new Properties();
                    try {
                        prop.load(CacheManager.class.getResourceAsStream("/application.properties"));
                        cacheSize = Integer.parseInt( prop.getProperty("lruCacheQueen.cacheSize"));
                    } catch (IOException e) {
                        cacheSize = 100;
                    }
                    singleton = new LruCacheQueen(cacheSize);
                }
            }
        }
        return singleton;
    }

    public static LruCache getLinkedCache() {
        if (cache == null) {
            synchronized (CacheManager.class) {
                if (cache == null) {
                    int cacheSize ;
                    Properties prop = new Properties();
                    try {
                        prop.load(CacheManager.class.getResourceAsStream("/application.properties"));
                        cacheSize = Integer.parseInt( prop.getProperty("lruCacheQueen.cacheSize"));
                    } catch (IOException e) {
                        cacheSize = 100;
                    }
                    cache = new LruCache<>(cacheSize);
                }
            }
        }
        return cache;
    }

}
