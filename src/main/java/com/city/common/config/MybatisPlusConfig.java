package com.city.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mirror6
 * @date 2020/4/6 10:26
 */

@Configuration
@MapperScan("com.city.system.mapper.*")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        // 开启 count 的 join 优化,只针对部分 left join
        interceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return interceptor;
    }

}

