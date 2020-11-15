package com.crossyf.dubbo.springbatch.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus 配置
 * @author zuos
 * @date 2020/2/5 2:33 上午
 */
@MapperScan(basePackages = {"com.crossyf.dubbo.springbatch.mapper"})
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptorMysql() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
//        page.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
//        page.setLimit(-1);
        // 开启 count 的 join 优化,只针对部分 left join
        page.setCountSqlParser(new JsqlParserCountOptimize(true));
        return page;
    }
}
