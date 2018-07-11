package com.lds.cache.component;


import sun.rmi.rmic.Main;

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

    public static LruCacheQueen getCache() {
        if (singleton == null) {
            synchronized (LruCacheQueen.class) {
                if (singleton == null) {
                    int cacheSize ;
                    Properties prop = new Properties();
                    try {
                        prop.load(Main.class.getResourceAsStream("/application.properties"));
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

}
