package com.yuntian.architecture.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * @Auther: yuntian
 * @Date: 2019/12/20 0020 21:55
 * @Description:
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "redis", ignoreUnknownFields = false)
@PropertySource(value = "classpath:config/redis.properties",encoding = "UTF-8")
@Data
public class RedisLocalProperties {

    /**
     * 前缀
     */
    private String keyPrefix;


}
