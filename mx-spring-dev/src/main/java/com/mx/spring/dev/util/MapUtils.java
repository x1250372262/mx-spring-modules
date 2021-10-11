package com.mx.spring.dev.util;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mengxiang.
 * @create: 2021-07-02 16:58
 * @Description:
 */
public class MapUtils<K, V> {

    private final Map<K, V> MAP = new HashMap<>();


    public Map<K, V> emptyMap() {
        return MAP;
    }

    public MapUtils<K, V> put(K key, V value) {
        MAP.put(key, value);
        return this;
    }

    public void remove(K key) {
        MAP.remove(key);
    }

    public Map<K, V> toMap() {
        return MAP;
    }

    public void foreach(Map<K, V> map, Foreach<K, V> foreach) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            WAY way = foreach.loop(entry.getKey(), entry.getValue());
            switch (way) {
                case BREAK:
                    break;
                case CONTINUE:
                    continue;
                default:
            }
        }
    }

    public void foreachKey(Map<K, V> map, ForeachKey<K, V> foreachKey) {
        for (K key : map.keySet()) {
            WAY way = foreachKey.loop(key);
            switch (way) {
                case BREAK:
                    break;
                case CONTINUE:
                    continue;
                default:
            }
        }
    }

    public void foreachValue(Map<K, V> map, ForeachValue<K, V> foreachValue) {
        for (V value : map.values()) {
            WAY way = foreachValue.loop(value);
            switch (way) {
                case BREAK:
                    break;
                case CONTINUE:
                    continue;
                default:
            }
        }
    }


    public enum WAY {
        /**
         * 终止方式
         */
        BREAK, CONTINUE, NONE;
    }

    public interface Foreach<K, V> {

        WAY loop(K key, V value);
    }

    public interface ForeachKey<K, V> {

        WAY loop(K key);
    }

    public interface ForeachValue<K, V> {

        WAY loop(V value);
    }
}
