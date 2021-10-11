package com.mx.spring.dev.support.redis.api;


import com.mx.spring.dev.exception.MxException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 18:59
 * @Description:
 */
public interface IRedisService {

    /**
     * 设置过期时间
     */
    Boolean expire(String key, long time);

    /**
     * 获取过期时间
     */
    Long getExpire(String key);

    void strSet(String key, Object value) throws MxException;

    void strSet(String key, Object value, long time) throws MxException;

    Long strIncr(String key, long delta) throws MxException;

    Long strDecr(String key, long delta) throws MxException;

    Object strGet(String key) throws MxException;

    void strDelete(String key) throws MxException;

    boolean strIsExist(String key) throws MxException;

    void strDeleteAll() throws MxException;

    Set<Object> setGet(String key) throws MxException;

    boolean setHasKey(String key, Object value) throws MxException;

    Long set(String key, Object... values) throws MxException;

    Long set(String key, long time, Object... values) throws MxException;

    Long getSetSize(String key) throws MxException;

    Long setRemove(String key, Object... values) throws MxException;

    List<Object> listGet(String key, long start, long end) throws MxException;

    Long listSize(String key) throws MxException;

    Object listIndex(String key, long index) throws MxException;

    void listSet(String key, Object value) throws MxException;

    void listSet(String key, Object value, long time) throws MxException;

    void listSet(String key, List<Object> value) throws MxException;

    void listSet(String key, List<Object> value, long time) throws MxException;

    void lisetUpdateIndex(String key, long index, Object value) throws MxException;

    Long listDelete(String key, long count, Object value) throws MxException;

    Object hashGet(String key, String item) throws MxException;

    Map<Object, Object> hashGet(String key) throws MxException;

    void hashSet(String key, Map<String, Object> map) throws MxException;

    void hashSet(String key, String item, Object value) throws MxException;

    void hashDel(String key, Object... item) throws MxException;

    boolean hashHasKey(String key, String item) throws MxException;

    double hashIncr(String key, String item, double by) throws MxException;

    double hasDecr(String key, String item, double by) throws MxException;

    /**
     * 分布式锁
     *
     * @param key
     * @param value
     * @return
     * @throws MxException
     */
    boolean lock(String key, String value) throws MxException;

    void unlock(String key, String value) throws MxException;

    Boolean delete(String key) throws MxException;


}
