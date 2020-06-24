package com.crossyf.dubbo.redis.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: User
 * @Auther: zhangyingqi
 * @Date: 2018/8/27 17:17
 * @Description:
 */

@Data
@Getter
@Setter
public class User{

    private String name;

    private int age;
}
