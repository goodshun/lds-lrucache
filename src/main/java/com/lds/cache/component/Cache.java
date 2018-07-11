package com.lds.cache.component;

/**
 * Title: Cache
 * <p>
 * Description:
 * </p>
 *
 * @author liudongshun
 * @version V1.0
 * @since 2018/07/11
 */
public interface Cache {

    public Object get(Object key);

    public Object put(Object key, Object value);

    public Object remove(Object key);
}
