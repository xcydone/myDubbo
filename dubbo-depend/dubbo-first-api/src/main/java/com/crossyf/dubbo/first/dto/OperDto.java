package com.crossyf.dubbo.first.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wy
 * 返回用户数据实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description= "返回参数定义")
public class OperDto implements Serializable {

    /**
     * 工号唯一标识（数字）
     */
    @ApiModelProperty(value = "工号唯一标识（数字）")
    private Integer operId;

    /**
     * 用户标识id（稽核系统专用）
     */
    @ApiModelProperty(value = "用户标识id（稽核系统专用）")
    private String userId;

    /**
     * 所属机构id
     */
    @ApiModelProperty(value = "所属机构id")
    private Integer deptNo;

    /**
     * 所属机构名称
     */
    @ApiModelProperty(value = "所属机构名称")
    private String deptName;
    //部门区域
    @ApiModelProperty(value = "/部门区域id")
    private Integer regionId;
    @ApiModelProperty(value = "/部门区域名称")
    private String regionName;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String operName;
    /**
     * 系统登录名称（无用暂留）
     */
    @ApiModelProperty(value = "系统登录名称（无用暂留）")
    private String loginName;

    /**
     * 无用暂留
     */
    @ApiModelProperty(value = "无用暂留")
    private String question;

    /**
     * 无用暂留
     */
    @ApiModelProperty(value = "无用暂留")
    private String answer;

    /**
     * 无用暂留
     */
    @ApiModelProperty(value = "无用暂留")
    private Integer needSms;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 座机号
     */
    @ApiModelProperty(value = "座机号")
    private String mobileNo;

    /**
     * 用户失效时间
     */
    @ApiModelProperty(value = "用户失效时间")
    private LocalDateTime effDate;

    /**
     * 对应CRM工号
     */
    @ApiModelProperty(value = "对应CRM工号")
    private String crmStaffId;
    @ApiModelProperty(value = "对应OA工号")
    private String oaStaffId;

    /**
     * 员工状态 1 有效 0 无效
     */
    @ApiModelProperty(value = "员工状态 1 有效 0 无效")
    private Integer staffStatus;

    /**
     * 判断是否给OA派单
     */
    @ApiModelProperty(value = "判断是否给OA派单")
    private Integer oaStatus;

    /**
     * 是否代理商（0否;1是）
     */
    @ApiModelProperty(value = "是否代理商（0否;1是）")
    private Integer isAgent;

    /**
     * 所属稽核模块，格式：*,1,x（1:业财）
     */
    @ApiModelProperty(value = "所属稽核模块，格式：*,1,x（1:业财）")
    private String moduleType;

    /**
     * 是否待办邮件通知
     */
    @ApiModelProperty(value = "是否待办邮件通知")
    private Integer isMail;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "录密码")
    private String operPwd;

    /**
     * 密码更新时间
     */
    @ApiModelProperty(value = "密码更新时间")
    private LocalDateTime pwdUpdateDate;

    //角色名称集合,用';'分割
    @ApiModelProperty(value = "角色名称集合,用';'分割")
    String role;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;
    @ApiModelProperty(value = "角色id集合")
    private List<Integer> roleIds;
}