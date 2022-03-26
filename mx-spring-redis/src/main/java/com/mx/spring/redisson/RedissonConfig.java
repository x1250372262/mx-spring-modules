package com.mx.spring.redisson;

import com.mx.spring.dev.exception.MxException;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private String database;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws MxException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        if (StringUtils.isNotEmpty(password)) {
            config.useSingleServer().setPassword(password);
        }
        config.useSingleServer().setDatabase(Integer.parseInt(database));
        return Redisson.create(config);
    }
}
