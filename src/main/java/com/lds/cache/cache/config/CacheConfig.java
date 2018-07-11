package com.lds.cache.cache.config;


/**
 * Title: CacheConfig
 * <p>
 * Description:
 * </p>
 *
 * @author liudongshun
 * @version V1.0
 * @since 2018/07/11
 */
public class CacheConfig {

    private int cacheSize;

    public int getCacheSize() {
        return cacheSize;
    }

    CacheConfig(int cacheSize){
        this.cacheSize = cacheSize;
    }

}
