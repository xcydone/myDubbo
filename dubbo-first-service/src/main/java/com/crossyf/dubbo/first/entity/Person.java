package com.crossyf.dubbo.first.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 1716947078039491122L;

    private String id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "地区")
    private String zone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "班级")
    private String classes;

    @ApiModelProperty(value = "等级")
    private String level;

    @ApiModelProperty(value = "母亲")
    private String mother;

    @ApiModelProperty(value = "母亲名称")
    private String motherName;

    @ApiModelProperty(value = "公司编码")
    private String companyCode;

    @ApiModelProperty(value = "公司名称")
    private String companyName;
}
