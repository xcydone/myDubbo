package com.crossyf.dubbo.common.response;

import com.crossyf.dubbo.common.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * REST API 返回结果
 * @author zuos
 * @date 2020/2/5 9:23 下午
 */
@Data
@ApiModel(description= "返回结构体参数定义")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 8708569195046968619L;
    @ApiModelProperty(value = "结果码")
    private Integer code;
    @ApiModelProperty(value = "结果信息描述")
    private String msg;
    @ApiModelProperty(value = "结果实体")
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
