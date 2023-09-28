package com.lopy.config.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Objects;

@Configuration
public class RedisConfig {

    @Resource
    private Environment environment;

    @Bean
    public GenericObjectPoolConfig<Object> redisPool() {
        return new GenericObjectPoolConfig<>();
    }

    /**
     * @description Config property for common redis
     * @author Dex
     * @date 2023/09/24
     */
    @Bean("redisCommonConfig")
    @Primary
    public RedisStandaloneConfiguration redisStandaloneConfig() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(Objects.requireNonNull(environment.getProperty("spring.redis.host")));
        config.setPassword(RedisPassword.of(environment.getProperty("spring.redis.password")));
        config.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("spring.redis.port"))));
        config.setDatabase(Integer.parseInt(Objects.requireNonNull(environment.getProperty("spring.redis.database"))));
        return config;
    }

    /**
     * @description Connection pool for common redis
     * @author Dex
     * @date 2023/09/24
     */
    @Bean("commonAuthLettuceConnectionFactory")
    @Primary
    public LettuceConnectionFactory commonLettuceConnectionFactory(GenericObjectPoolConfig<Object> redisPool, @Qualifier("redisCommonConfig") RedisStandaloneConfiguration redisCommonConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPool).build();
        return new LettuceConnectionFactory(redisCommonConfig, clientConfiguration);
    }

    /**
     * @description Build common redis
     * @author Dex
     * @date 2023/09/24
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, String> redisTemplate(@Qualifier("commonAuthLettuceConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return buildStringRedisTemplate(redisConnectionFactory);
    }

    /**
     * @description Set serializer as string for redis key & value
     * @author Dex
     * @date 2023/09/24
     */
    private RedisTemplate<String, String> buildStringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
