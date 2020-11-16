package com.crossyf.dubbo.web.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: lsl
 * @Date: 2020\2\21 0021 11:46
 * @Version: 1.0
 * @Description: 登录返回前端对象
 */
@Data
@ApiModel(value = "登录返回前端对象")
public class LoginRespDto {
    private Integer operId;

    private String operName;

    private String url;

    private String userId;
}
