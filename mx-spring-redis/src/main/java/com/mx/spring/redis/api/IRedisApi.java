package com.mx.spring.redis.api;


import com.mx.spring.dev.exception.MxException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: mengxiang.
 * @create: 2021-09-04 18:59
 * @Description:
 */
public interface IRedisApi {

    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     * @return
     */
    Boolean expire(String key, long time);

    /**
     * 获取过期时间
     *
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 新增一个字符串类型的值
     *
     * @param key
     * @param value
     * @throws MxException
     */
    void strSet(String key, Object value) throws MxException;

    /**
     * 新增一个字符串类型的值
     *
     * @param key
     * @param value
     * @param time
     * @throws MxException
     */
    void strSet(String key, Object value, long time) throws MxException;


    /**
     * 以增量的方式
     *
     * @param key
     * @param delta
     * @return
     * @throws MxException
     */
    Long strIncr(String key, long delta) throws MxException;

    /**
     * strIncr反之
     *
     * @param key
     * @param delta
     * @return
     * @throws MxException
     */
    Long strDecr(String key, long delta) throws MxException;

    /**
     * 获取一个字符串类型的值
     *
     * @param key
     * @return
     * @throws MxException
     */
    Object strGet(String key) throws MxException;

    /**
     * 删除一个字符串类型的值
     *
     * @param key
     * @throws MxException
     */
    void strDelete(String key) throws MxException;

    /**
     * 判断字符串值是否存在
     *
     * @param key
     * @return
     * @throws MxException
     */
    boolean strIsExist(String key) throws MxException;

    /**
     * 删除所有的字符串值
     *
     * @throws MxException
     */
    void strDeleteAll() throws MxException;

    /**
     * 获取key中的值
     *
     * @param key
     * @return
     * @throws MxException
     */
    Set<Object> setGet(String key) throws MxException;

    /**
     * 判断是否存在
     *
     * @param key
     * @param value
     * @return
     * @throws MxException
     */
    boolean setHasKey(String key, Object value) throws MxException;

    /**
     * 向key中批量添加值
     *
     * @param key
     * @param values
     * @return
     * @throws MxException
     */
    Long set(String key, Object... values) throws MxException;

    /**
     * 向key中批量添加值
     *
     * @param key
     * @param time
     * @param values
     * @return
     * @throws MxException
     */
    Long set(String key, long time, Object... values) throws MxException;

    /**
     * 获取长度
     *
     * @param key
     * @return
     * @throws MxException
     */
    Long getSetSize(String key) throws MxException;

    /**
     * 删除
     *
     * @param key
     * @param values
     * @return
     * @throws MxException
     */
    Long setRemove(String key, Object... values) throws MxException;

    /**
     * 获取list值
     *
     * @param key
     * @param start
     * @param end
     * @return
     * @throws MxException
     */
    List<Object> listGet(String key, long start, long end) throws MxException;

    /**
     * 获取大小
     *
     * @param key
     * @return
     * @throws MxException
     */
    Long listSize(String key) throws MxException;

    /**
     * 获取索引对应的值
     *
     * @param key
     * @param index
     * @return
     * @throws MxException
     */
    Object listIndex(String key, long index) throws MxException;

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @throws MxException
     */
    void listSet(String key, Object value) throws MxException;

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @param time
     * @throws MxException
     */
    void listSet(String key, Object value, long time) throws MxException;

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @throws MxException
     */
    void listSet(String key, List<Object> value) throws MxException;

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @param time
     * @throws MxException
     */
    void listSet(String key, List<Object> value, long time) throws MxException;

    /**
     * 修改索引对应的值
     *
     * @param key
     * @param index
     * @param value
     * @throws MxException
     */
    void lisetUpdateIndex(String key, long index, Object value) throws MxException;

    /**
     * 删除
     *
     * @param key
     * @param count
     * @param value
     * @return
     * @throws MxException
     */
    Long listDelete(String key, long count, Object value) throws MxException;

    /**
     * 获取hash值
     *
     * @param key
     * @param item
     * @return
     * @throws MxException
     */
    Object hashGet(String key, String item) throws MxException;

    /**
     * 获取hash值
     *
     * @param key
     * @return
     * @throws MxException
     */
    Map<Object, Object> hashGet(String key) throws MxException;

    /**
     * 设置hash值
     *
     * @param key
     * @param map
     * @throws MxException
     */
    void hashSet(String key, Map<String, Object> map) throws MxException;

    /**
     * 设置hash值
     *
     * @param key
     * @param item
     * @param value
     * @throws MxException
     */
    void hashSet(String key, String item, Object value) throws MxException;

    /**
     * 删除hash值
     *
     * @param key
     * @param item
     * @throws MxException
     */
    void hashDel(String key, Object... item) throws MxException;

    /**
     * 是否包含
     *
     * @param key
     * @param item
     * @return
     * @throws MxException
     */
    boolean hashHasKey(String key, String item) throws MxException;

    /**
     * 增量方式
     *
     * @param key
     * @param item
     * @param by
     * @return
     * @throws MxException
     */
    double hashIncr(String key, String item, double by) throws MxException;

    /**
     * hashIncr反之
     *
     * @param key
     * @param item
     * @param by
     * @return
     * @throws MxException
     */
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

    /**
     * 解锁
     *
     * @param key
     * @param value
     * @throws MxException
     */
    void unlock(String key, String value) throws MxException;

    /**
     * 删除一个值
     *
     * @param key
     * @return
     * @throws MxException
     */
    Boolean delete(String key) throws MxException;


}
