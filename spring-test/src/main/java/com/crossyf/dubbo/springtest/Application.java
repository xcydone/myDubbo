package com.crossyf.dubbo.springtest;

import cn.hutool.extra.spring.SpringUtil;
import com.crossyf.dubbo.springtest.config.*;
import com.crossyf.dubbo.springtest.test.beanTest.FirstBean;
import com.crossyf.dubbo.springtest.test.beanTest.MyBean;
import com.crossyf.dubbo.springtest.test.testEmail.SendEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
		// context.getBean("myBean");
		context.getBean(MyBean.class);
		context.close();*/

		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 激活 like 的profile
		context.getEnvironment().setActiveProfiles("wenke");
		context.register(AppConfigWithActiveProfile.class, AppConfigWithInActiveProfile.class);
		context.refresh();
		Subject subject = (Subject) context.getBean("subject");
		System.out.println("subject = " + subject);*/

		// ------------------------------ 测试scope  ------------------------------
		/*ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigWithAliasAndScope.class);
		MyBean myBean = (MyBean) context.getBean("b1");
		MyBean myBean2 = (MyBean) context.getBean("b2");
		System.out.println(myBean);
		System.out.println(myBean2);*/


		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfigWithLazy.class);

		// 获取启动过程中的bean 定义的名称
		for(String str : context.getBeanDefinitionNames()){
			System.out.println("str = " + str);
		}*/

		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfigWithPrimary.class);
		*//*MyBean bean = context.getBean(MyBean.class);*//*
		MyBean bean = (MyBean)context.getBean("myBeanOne");
		System.out.println(bean);*/


		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfigWithDependsOn.class);
		context.getBean(FirstBean.class);
		context.close();*/

		/*ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		SendEmail sendEmail = context.getBean(SendEmail.class);
		try{
			sendEmail.send();
		}catch (Exception e){
			e.printStackTrace();
		}*/

	}

}
