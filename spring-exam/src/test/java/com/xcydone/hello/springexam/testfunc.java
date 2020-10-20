package com.xcydone.hello.springexam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringexamApplication.class)
public class testfunc {

    /*@Autowired
    public Popo popo;*/

    @Test
    public void testMybatis() {
        System.out.println("nihao");
    }
}
