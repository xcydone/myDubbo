package com.crossyf.dubbo.server.config;

import com.crossyf.dubbo.common.interceptor.GlobalInterceptor;
import com.crossyf.dubbo.common.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${isInterceptor}")
    public boolean isInterceptor;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new GlobalInterceptor(jwtUtil, isInterceptor));
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/login/*",            //登录
                "/api/**",                 //外部接口服务
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",           //css静态资源
                "/**/*.png", "/**/*.jpg", "/**/*.jpeg",     //图片资源
                "/swagger-resources/**", "/webjars/**", "/swagger-ui.html/**" //swagger资源
               );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
