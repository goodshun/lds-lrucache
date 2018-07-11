package com.lds.cache.component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title: LruCache
 * <p>
 * Description:
 * 基于LinkedHashMap 实现
 * </p>
 *
 * @author liudongshun
 * @version V1.0
 * @since 2018/07/02
 */
public class LruCache<k,v> extends LinkedHashMap<k,v> {
    private final int maxCapacity;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final Lock lock = new ReentrantLock();

    public LruCache(int maxCapacity) {
        super(maxCapacity,DEFAULT_LOAD_FACTOR,true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<k, v> eldest) {
        return size() > maxCapacity;
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            lock.lock();
            return super.containsKey(key);
        }finally {
            lock.unlock();
        }
    }


    @Override
    public v get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public v put(k key, v value) {
        try {
            lock.lock();
            return super.put(key, value);
        }finally {
            lock.unlock();
        }
    }
}
