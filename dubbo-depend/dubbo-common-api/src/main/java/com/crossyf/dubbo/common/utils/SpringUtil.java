package com.crossyf.dubbo.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * ApplicationContextAware实现类，提供getBean方法
 * </p>
 *
 * @author zuos
 * @since 2020-02-22
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (context == null) {
            context = applicationContext;
        }
    }

    public static Object getBean(String beanName) {
        if (context == null) {
            return null;
        }
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        if (context == null) {
            return null;
        }
        return context.getBean(clazz);
    }
}
