package com.yuntian.architecture.redis;

import com.yuntian.architecture.redis.config.RedisRemoteProperties;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class CommonRedisApplicationTests {

    @Resource
    private RedisRemoteProperties redisRemoteProperties;

    @Test
    void contextLoads() {
        System.out.println(redisRemoteProperties.getKeyPrefix());
    }
}
