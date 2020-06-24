package com.crossyf.dubbo.first.item;

import com.crossyf.dubbo.common.annotation.OperType;
import com.crossyf.dubbo.common.utils.ClassScanner;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderItemProcess implements BeanFactoryPostProcessor {
    private static final String SCANPATH = "com.crossyf.dubbo.first.item.impl";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        Map<Integer,Class<IOrderItemHandle>> handleMap = Maps.newHashMapWithExpectedSize(8);

        ClassScanner.scan(SCANPATH, OperType.class).forEach( clazz ->{
            Class<IOrderItemHandle> cal = (Class<IOrderItemHandle>)clazz;
            Integer operType = cal.getAnnotation(OperType.class).value();
            handleMap.put(operType, cal);
        });

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(OrderItemContent.class);
        builder.addPropertyValue("handleMap", handleMap);
        DefaultListableBeanFactory defaultBeanFactory = (DefaultListableBeanFactory)beanFactory;
        defaultBeanFactory.registerBeanDefinition(OrderItemContent.class.getName(), builder.getBeanDefinition());
    }
}
