package com.yuntian.architecture.redis;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@NacosPropertySource(dataId = "redis", groupId= "common-redis",  autoRefreshed = true)
@SpringBootApplication
public class CommonRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonRedisApplication.class, args);
    }

}
