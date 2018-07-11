package com.lds.cache.component;

import org.springframework.beans.factory.annotation.Value;


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
