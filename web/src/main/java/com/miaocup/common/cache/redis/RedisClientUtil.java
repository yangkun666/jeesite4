package com.miaocup.common.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by S on 2017/5/27.
 */
@Component
public class RedisClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisClientUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static int EXPIRE_PERIOD_MIS = 60 * 1000 * 60 * 2;
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



/*    public boolean set(final string key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 追加内容
     *
     * @param key
     * @param value
     * @return
     */
    public boolean append(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.append(key, value);
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


/*    public boolean set(final string key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/


    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
/*    public boolean setStr(final string key, string value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<string, string> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
/*    public void remove(final string key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }*/

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        boolean flag =exists(key);
        if (flag) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        String result = null;
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }


/*    public Object get(final string key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }*/


    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public <T> T getByKey(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return (T) result;
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, String hashKey, String value, Long expireTime) {
        HashOperations<String, String, String> hash = stringRedisTemplate.opsForHash();
        hash.put(key, hashKey, value);
        stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public String hmGet(String key, String hashKey) {
        HashOperations<String, String, String> hash = stringRedisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @return
     */
    public Map<String, String> hmGetAll(String key) {
        HashOperations<String, String, String> hash = stringRedisTemplate.opsForHash();
        return hash.entries(key);
    }

    /**
     * 列表添加
     */
    public void lPush(String k, String v) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 列表获取
     */
    public String rPop(String k) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        return list.rightPop(k);
    }

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> set = redisTemplate.opsForZSet();
        set.add(key, value, scoure);
    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    /**
     * 哈希 删除
     *
     * @param key
     * @param hashKey
     */
    public void hmDel(String key, String hashKey) {
        HashOperations<String, String, String> hash = stringRedisTemplate.opsForHash();
        hash.delete(key, hashKey);
    }

    public synchronized boolean lock(String lockKey,long lockTime) {
        Boolean flag = (Boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            StringRedisSerializer serializer = new StringRedisSerializer();
            Boolean success = connection.setNX(serializer.serialize(lockKey), serializer.serialize(String.valueOf(lockTime)));
            connection.close();
            return success;
        });
        if(flag){
            //logger.info("开始设置过期时间 lockKey：{} lockTime:{}",lockKey,lockTime);
            //设置超时时间，释放内存
            redisTemplate.expire(lockKey, lockTime, TimeUnit.SECONDS);
        }
        return flag;
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setNXStr(final String key, final String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(serializer.serialize(key), serializer.serialize(value));
                    //Boolean success = connection.hSetNX(serializer.serialize(key), serializer.serialize(value),serializer.serialize("10000"));
                    connection.close();
                    return success;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj != null ? (Boolean) obj : false;
    }

    public void deleteByPrex(String prex) {
        String key = prex+"*";
        Set<String> keys=redisTemplate.keys(key);
        redisTemplate.delete(keys);
    }

    public void deleteBySuffix(String suffix) {
        Set<String> keys=redisTemplate.keys("*"+suffix);
        redisTemplate.delete(keys);
    }
    public void deleteByKeys(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }

    public void incr(String key){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.increment(key , 1);
    }

    /**
     * 获取key超时时间
     */
    public long ttl(String key) {
        try {
            return (Long) redisTemplate.execute((RedisCallback) connection -> {
                StringRedisSerializer serializer = new StringRedisSerializer();
                return connection.ttl(serializer.serialize(key));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设置超时时间
     */
    public void expire(String key, Long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }
}
