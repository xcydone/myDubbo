package com.crossyf.dubbo.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录请求实体类
 * @author lsl
 */
@Data
@ApiModel(value = "登录实体类")
public class LoginDto {
    /*@ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名称不能为空")
    private String userId;

    @ApiModelProperty(value = "用户密码(已加密)")
    @NotEmpty(message = "用户密码不能为空")
    private String operPwd;*/

    @ApiModelProperty(value = "验证码")
    @NotEmpty(message = "验证码不能为空")
    private String verificationCode;

    @ApiModelProperty(value = "验证码加密后字符串")
    @NotEmpty(message = "随机数不能为空")
    private String randnum;
}
