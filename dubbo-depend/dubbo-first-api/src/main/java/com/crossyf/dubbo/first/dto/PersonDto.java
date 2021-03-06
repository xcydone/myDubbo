package com.crossyf.dubbo.first.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description= "返回参数定义")
public class PersonDto implements Serializable {
    private static final long serialVersionUID = -3117083718960203982L;

    private String id;

    @ApiModelProperty(value = "创建时间")
    private LocalDate createTime;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "班级")
    private String classes;

    @ApiModelProperty(value = "等级")
    private String level;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "母亲")
    private String mother;

    @ApiModelProperty(value = "父亲")
    private String father;

    @ApiModelProperty(value = "公司编码")
    private String companyCode;
}
