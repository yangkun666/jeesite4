package com.miaocup.common.cache.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by S on 2017/5/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RedisProperties {
    @Value("${redis.pool.minIdle}")
    private int minIdle;
    @Value("${redis.pool.maxIdle}")
    private int maxIdle;
    @Value("${redis.pool.maxTotal}")
    private int maxTotal;
    @Value("${redis.timeout}")
    private int maxWaitMillis = 3000;
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private  int port;
    @Value("${redis.password}")
    private String password;
}
