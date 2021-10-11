package com.mx.spring.dev.support.redis.api.impl;

import cn.hutool.core.convert.Convert;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.redis.api.IRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public void strSet(String key, Object value) throws MxException {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void strSet(String key, Object value, long time) throws MxException {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
    }

    @Override
    public Long strIncr(String key, long delta) throws MxException {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long strDecr(String key, long delta) throws MxException {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    @Override
    public Object strGet(String key) throws MxException {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void strDelete(String key) throws MxException {
        redisTemplate.delete(key);
    }

    @Override
    public boolean strIsExist(String key) throws MxException {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    @Override
    public void strDeleteAll() throws MxException {
        Set<String> keys = redisTemplate.keys("*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public Set<Object> setGet(String key) throws MxException {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public boolean setHasKey(String key, Object value) throws MxException {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }

    @Override
    public Long set(String key, Object... values) throws MxException {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long set(String key, long time, Object... values) throws MxException {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Long getSetSize(String key) throws MxException {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Long setRemove(String key, Object... values) throws MxException {
        return redisTemplate.opsForSet().remove(key, values);
    }

    @Override
    public List<Object> listGet(String key, long start, long end) throws MxException {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long listSize(String key) throws MxException {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public Object listIndex(String key, long index) throws MxException {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public void listSet(String key, Object value) throws MxException {
        redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public void listSet(String key, Object value, long time) throws MxException {
        redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
    }

    @Override
    public void listSet(String key, List<Object> value) throws MxException {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    @Override
    public void listSet(String key, List<Object> value, long time) throws MxException {
        redisTemplate.opsForList().rightPushAll(key, value);
        expire(key, time);
    }


    @Override
    public void lisetUpdateIndex(String key, long index, Object value) throws MxException {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public Long listDelete(String key, long count, Object value) throws MxException {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    @Override
    public Object hashGet(String key, String item) throws MxException {
        return redisTemplate.opsForHash().get(key, item);
    }

    @Override
    public Map<Object, Object> hashGet(String key) throws MxException {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void hashSet(String key, Map<String, Object> map) throws MxException {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hashSet(String key, String item, Object value) throws MxException {
        redisTemplate.opsForHash().put(key, item, value);
    }

    @Override
    public void hashDel(String key, Object... item) throws MxException {
        redisTemplate.opsForHash().delete(key, item);
    }

    @Override
    public boolean hashHasKey(String key, String item) throws MxException {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    @Override
    public double hashIncr(String key, String item, double by) throws MxException {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    @Override
    public double hasDecr(String key, String item, double by) throws MxException {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    @Override
    public boolean lock(String key, String value) throws MxException {
        if (Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value))) {
            return true;
        }
        String redisValue = Convert.toStr(redisTemplate.opsForValue().get(key));
        if (StringUtils.isNotBlank(redisValue) && Long.parseLong(redisValue) < System.currentTimeMillis()) {
            String nowValue = Convert.toStr(redisTemplate.opsForValue().getAndSet(key, value));
            return nowValue != null && nowValue.equals(redisValue);
        }
        return false;
    }

    @Override
    public void unlock(String key, String value) throws MxException {
        String redisValue = Convert.toStr(redisTemplate.opsForValue().get(key));
        if (StringUtils.isNotBlank(redisValue) && redisValue.equals(value)) {
            redisTemplate.opsForValue().getOperations().delete(key);
        }
    }

    @Override
    public Boolean delete(String key) throws MxException {
        return redisTemplate.delete(key);
    }
}
