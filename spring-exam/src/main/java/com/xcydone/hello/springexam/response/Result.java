package com.xcydone.hello.springexam.response;

import com.xcydone.hello.springexam.constant.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 8708569195046968619L;

    private Integer code;
    private String msg;
    private T data;

    private Result() {
        this.setCode(ResultEnum.SUCCESS.getCode());
        this.setMsg(ResultEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> ok() {
        return new Result();
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail() {
        Result<T> result = new Result();
        result.setCode(ResultEnum.FAILURE.getCode());
        result.setMsg(ResultEnum.FAILURE.getMsg());
        return result;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result();
        result.setCode(ResultEnum.FAILURE.getCode());
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
