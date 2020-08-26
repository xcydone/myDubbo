package com.crossyf.dubbo.springtest.test.testSeriliaze;

import lombok.Data;

import java.io.Serializable;

@Data
public class FlyPig implements Serializable {
    private static final long serialVersionUID = 1L;

    // 静态变量 不序列化 -- 先执行序列化写文件是269，修改值为26，再执行反序列化读文件，实际还是 26
    private static String AGE = "26";

    private String name;
    private String color;

    // transient修饰的属性不能被序列化
    transient private String car;

    private String addTip;

    @Override
    public String toString() {
        return "FlyPig{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", car='" + car + '\'' +
                ", AGE='" + AGE + '\'' +
                ", addTip='" + addTip + '\'' +
                '}';
    }
}
