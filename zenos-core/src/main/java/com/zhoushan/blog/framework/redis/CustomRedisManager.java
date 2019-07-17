package com.zhoushan.blog.framework.redis;

import lombok.Data;
import org.crazycake.shiro.RedisManager;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * @author zhooo
 * @version V1.0
 * @Title: CustomRedisManager
 * @Package com.zhoushan.blog.framework.redis
 * @Description: 自定义RedisManager
 * @date 7/14/2019 6:26 PM
 */
@Data
public class CustomRedisManager extends RedisManager {
    private static JedisPool jedisPool = null;
    private String host = "127.0.0.1";
    private int port = 6379;
    private int expire = 2592000;
    private int timeout = 2000;
    private int database = 0;
    private String password = null;

    public CustomRedisManager() {

    }

    @Override
    public void init() {
        this.password = StringUtils.isEmpty(this.password) ? null : this.password;
        if (jedisPool == null) {
            jedisPool = new JedisPool(new JedisPoolConfig(), this.host, this.port, this.timeout, this.password, this.database);
        }
    }

    @Override
    public byte[] get(byte[] key) {
        byte[] value = null;

        try (Jedis jedis = jedisPool.getResource()) {
            value = jedis.get(key);
        }
        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        }
        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        }
        return value;
    }

    @Override
    public void del(byte[] key) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        }
    }

    @Override
    public void flushDB() {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.flushDB();
        }
    }

    @Override
    public Long dbSize() {
        Long dbSize = 0L;
        try (Jedis jedis = jedisPool.getResource()) {
            dbSize = jedis.dbSize();
        }
        return dbSize;
    }

    @Override
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        try (Jedis jedis = jedisPool.getResource()) {
            keys = jedis.keys(pattern.getBytes());
        }
        return keys;
    }
}
