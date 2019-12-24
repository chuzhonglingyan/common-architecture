package com.yuntian.architecture.redis.config;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

/**
 * @Auther: yuntian
 * @Date: 2019/12/20 0020 21:55
 * @Description:
 */
@Data
public class RedisRemoteProperties {

    /**
     * 前缀
     */
    @Value("${redis.keyPrefix}")
    private String keyPrefix;

}
