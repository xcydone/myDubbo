package com.crossyf.dubbo.springtest.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = {"com.crossyf.dubbo.springtest.mapper"})
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptorMysql() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        page.setCountSqlParser(new JsqlParserCountOptimize(true));
        return page;
    }
}
