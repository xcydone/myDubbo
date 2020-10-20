package com.config;

import com.yz.user.impl.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext("config");
        AccountService account = ac.getBean(AccountService.class);
        account.saveAccount();
    }


}
