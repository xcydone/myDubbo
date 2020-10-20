package com.xcydone.hello.springexam.constant;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200, "SUCCESS"),
    FAILURE(300, "出错了，请联系管理员！");

    private Integer code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
