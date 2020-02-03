package com.yuntian.architecture.data.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.yuntian.architecture.data.config.addmethod.LogicDeleteByIdWithFill;
import com.yuntian.architecture.data.config.addmethod.LogicDeleteByIdsWithFill;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

/**
 * @Auther: yuntian
 * @Date: 2019/6/25 0025 21:04
 * @Description: 数据库操作
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public ISqlInjector logicSqlInjector() {
        return new DefaultSqlInjector() {

            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass);
                methodList.add(new LogicDeleteByIdWithFill());
                methodList.add(new LogicDeleteByIdsWithFill());
                return methodList;
            }
        };
    }


}

