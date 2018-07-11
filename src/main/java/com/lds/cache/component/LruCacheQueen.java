package com.lds.cache.component;

import lombok.Data;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title: LruCacheQueen
 * <p>
 * Description:
 * 基于concurrentHashMap存储  自我实现双链表
 * </p>
 *
 * @author liudongshun
 * @version V1.0
 * @since 2018/07/02
 */

public class LruCacheQueen implements Cache{

    private int cacheSize;
    private HashMap nodeTable;
    private volatile int currentSize;
    private CacheNode first;
    private CacheNode last;

    private ReentrantLock lock = new ReentrantLock();


    @Data
    class CacheNode {
        /**
         * 前一个节点
         */
        CacheNode prev;

        /**
         * 后一个节点
         */
        CacheNode next;

        Object value;

        Object key;

        CacheNode() {

        }
    }


    public LruCacheQueen(int maxCapacity) {
        currentSize = 0;
        this.cacheSize = maxCapacity;
        nodeTable = new HashMap(maxCapacity);
    }


    /**
     * 获取 value
     *
     * @param key
     * @return
     */
    @Override
    public Object get(Object key) {
        CacheNode node = (CacheNode) nodeTable.get(key);
        if (node != null) {
            moveToHead(node);
            return node.getValue();
        }
        return null;
    }


    @Override
    public Object put(Object key, Object value) {
        CacheNode node = (CacheNode) nodeTable.get(key);
        try {
            if (node != null) {
                node.setValue(value);
                moveToHead(node);
            } else {
                lock.lock();
                if (currentSize >= cacheSize) {
                    //超过容量 ，删去
                    if (last != null) {
                        removeLast();
                    }
                } else {
                    currentSize++;
                }
                node = new CacheNode();
                node.setKey(key);
                node.setValue(value);
                if (first != null){
                    CacheNode nodeTemp = first;
                    node.next = nodeTemp;
                    nodeTemp.prev = node;
                    if (nodeTemp.next == null){
                        last = nodeTemp;
                    }
                    first = node;
                }else {
                    first = node;
                }

                nodeTable.put(key, node);

            }
        } finally {
            lock.unlock();
        }
        return node;
    }


    @Override
    public Object remove(Object key) {

        CacheNode node = (CacheNode) nodeTable.get(key);
        try {
            lock.lock();
            if (node != null) {
                if (node == first) {
                    first = first.next;
                    first.prev = null;
                } else if (node == last) {
                    removeLast();
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                nodeTable.remove(key);
            }
        } finally {
            lock.unlock();
        }
        return node;
    }

    /**
     * 删除最少使用key
     *
     * @return
     */
    private Object removeLast() {
        try {
            lock.lock();
            if (last != null) {
                nodeTable.remove(last.key);
                if (last.prev != null) {
                    last.prev.next = null;
                } else {
                    first = null;
                }
                last = last.prev;

            }
        } finally {
            lock.unlock();
        }
        return null;
    }


    private void moveToHead(CacheNode node) {

        if (node == first) {
            return;
        }
        try {
            lock.lock();
            if (node.next != null) {
                node.prev.next = node.next;
            }
            if (node.prev != null) {
                node.next.prev = node.prev;
            }
            if (last == node) {
                last.prev = node.prev;
            }
            if (first != null) {
                node.next = first;
                first.prev = node;
            }
            first = node;
            node.prev = null;
            if (last == null) {
                last = first;
            }
        } finally {
            lock.unlock();
        }


    }


}
