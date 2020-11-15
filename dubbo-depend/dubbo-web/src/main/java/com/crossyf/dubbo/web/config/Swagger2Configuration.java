package com.crossyf.dubbo.web.config;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ConfigurationProperties(prefix = "swagger")
@Configuration
@EnableSwagger2
@Data
public class Swagger2Configuration {

    /*@Value("${swagger.basePackage}")
    private String  basePackage;*/

    private String  basePackage;

    private String  title;

    private String  description;

    private String  version;

    private boolean enable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title) //设置文档的标题
                .description(description) // 设置文档的描述
                .version(version) // 设置文档的版本信息-> 1.0.0 Version information
                .build();
    }
}
