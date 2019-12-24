package com.yuntian.architecture.redis.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.yuntian.architecture.redis.lock.RedissLockUtil;
import com.yuntian.architecture.redis.lock.RedissonDistributedLocker;

import org.redisson.api.RedissonClient;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/8/17 0017 22:14
 * @Description:
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Resource
    private RedisRemoteProperties redisProperties;


    @Bean(name = "redisTemplate")
    public <V> RedisTemplate<String, V> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        FastJsonRedisSerializer<V> fastJsonRedisSerializer = new FastJsonRedisSerializer<>();
        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new MyStringSerializer(redisProperties.getKeyPrefix()));
        redisTemplate.setHashKeySerializer(new MyStringSerializer(redisProperties.getKeyPrefix()));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    RedisManage redisManage() {
        return new RedisManage();
    }


    @Bean
    RedissonDistributedLocker redissonDistributedLocker(RedissonClient redissonClient) {
        RedissonDistributedLocker redissonDistributedLocker = new RedissonDistributedLocker();
        redissonDistributedLocker.setRedissonClient(redissonClient);
        RedissLockUtil.setLocker(redissonDistributedLocker);
        return redissonDistributedLocker;
    }


    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        // 异常处理，当Redis发生异常时，打印日志，但是程序正常走
        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheClearError：", e);
            }
        };
    }


    /**
     * 配置spring boot的注解，进行方法级别的缓存
     * 使用：进行分割，可以很多显示出层级关系
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(":").append(String.valueOf(obj));
            }
            String rsToUse = String.valueOf(sb);
            log.info("自动生成Redis Key -> [{}]", rsToUse);
            return rsToUse;
        };
    }


}
