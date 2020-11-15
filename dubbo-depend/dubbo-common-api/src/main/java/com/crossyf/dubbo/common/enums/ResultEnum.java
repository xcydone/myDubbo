package com.crossyf.dubbo.common.enums;

import lombok.Getter;

/**
 * 返回结果
 * @author zuos
 * @date 2020/2/3 3:44 下午
 */
@Getter
public enum ResultEnum {
    SUCCESS(200, "SUCCESS"),

    TOKEN_ERROR(1001, "会话已过期，请重新登录"),

    FAILURE(3000, "出错了，请联系管理员！"),

    SYSTEM_EXCEPTION(5000,"系统出错了！"),

    SYSTEM_DELETE_ROLE_FAIL(5002,"管理员角色不能删除！"),

    PARAMETER_ERROR(3001, "参数校验失败"),

    BUSINESS_ERROR(4001, "业务处理失败"),

    RECALLORDER_ERROR(6001,"工单已归档，不能撤回！"),

    RECALLORDER_FIALURE(6002,"下个环节已处理，不能撤回！"),

    ORDERLIST_ERROR(6003,"下个环节的工单为空，系统错误请联系管理员！"),

    ORDER_ITEM_FORWARD_LAST_INSTANCE_BACK(6004,"本环节的上一环节是回退，因此本环节不能转发给他人"),

    ORDER_FILE_ERROR(6005, "附件上传失败，请稍候再试！"),

    ORDER_HANDLE_NOT_EXIST(6006, "此环节已被其他人处理！"),

    FIALURE_RECALLORDER(6008,"该工单已经撤回，操作失败！"),

    FIALURE_RECALLORDER_BACKORDER(6009,"该工单为回退类型，不能撤回！"),

    FIALURE_RECALL_ORDER(6010,"该工单环节其他操作员处理完成，不能撤回！"),

    WORKFLOW_ITEM_NOT_EXIST(6011, "根据工单流程ID未查询到工单环节"),

    WORKFLOW_NOT_EXIST(6007, "工单流程信息不存在，请稍后重试！"),

    ORDER_ITEM_NOT_EXIST(6009, "未查询到下一个环节信息，请稍后重试！"),

    DELETE_OPER_REC_ERROR(3002, "用户已配置工单操作权限,不能被删除!"),

    UPDATE_OPER_REC_INVALID_ERROR(3003, "用户已配置工单操作权限,不能被置为无效!"),

    SYS_DELETE_ERROR(4001, "系统意见不能被删除!"),

    SYS_CONF_RULE_DELETE_ERROR(4002, "规则表管理配置信息删除失败！"),

    SYS_CONF_RULE_ADD_FAIL(4003, "规则表管理配置信息新增失败！"),

    SYS_CONF_RULE_NAME_REPEAT(4004, "规则管理名称已存在！"),

    NO_ITEM_OPER(6011, "此环节没有操作人!"),

    NO_FRONT_ITEM(6012, "无上一环节!"),

    NO_NEXT_ITEM(6013, "无下一环节!"),

    NO_ITEM_OPER_CONFIG(6014, "未配置环节相关处理人!"),

    REPORT_SHEET_ADD_FIAL(7001, "报表基础配置信息新增失败！"),

    REPORT_SHEET_DELETE_FIAL(7002, "报表基础配置信息删除失败！"),

    REPORT_SHEET_MODIFY_FIAL(7003, "报表基础配置信息修改失败！"),

    REPORT_SHEET_NAME_EXIST (7004, "报表名称已存在"),

    REPORT_SHOW_ERROR (7005, "未查询到报表配置信息"),

    REPORT_SHOW_SQL_NULL (7006, "报表查询SQL为空"),

    REPORT_FILE_ERROR(7007, "sql文件上传失败，请稍候再试！"),

    REPORT_INITPROCNAME_NULL(7008, "脚本执行不能为空！"),

    MENU_NOT_EXIST(8000, "菜单不存在！"),

    PARENT_MENU_ID_ISNULL(8001, "菜单父节点为空！"),

    TABLE_NOT_EXIST(8002, "数据库中规则主表不存在！"),

    NO_MATCH_CONFIG(9001, "该稽核点没有配置对应的规则!"),

    NO_PRK(9002, "该表未配置主键列!"),

    CLASS_TYPE_MANY_DIFF_THRESHOLD_VALUE(9003, "该所属大类配置阀值过多!"),

    FAIL_MAKE_QC_FILE(9004,"生成QC文件失败！"),

    FAIL_MAKE_TXT_FILE(9005,"生成TXT文件失败"),

    BUSINESS_DETAIL_TYPE_MANY_DIFF_THRESHOLD_VALUE(9006,"该业务小类配置阀值过多!"),

    BUSINESS_TYPE_MANY_DIFF_THRESHOLD_VALUE(9007,"该业务大类配置阀值过多!"),

    NO_DIFF_THRESHOLD_VALUE(9008,"未查询到阀值"),

    FAIL_SELECT_FILE_SEQ(9009,"查询文件编号失败！"),

    FAIL_ADT_WORKFLOW_REC(9010,"adt_workflow_rec配置有误！"),

    NO_AUDIT_CONFIG(9011,"稽核点没有配置对应的匹配规则！"),

    MATCH_DATA_IS_OLD(9012,"该页面不是最新数据，请刷新页面后再操作!"),

    ORDER_ITEM_DEALING_BY_OTHER(9013, "已有其他处理人正在处理！"),

    NO_PERMISSION(10000,"您没有权限访问!"),

    PARAM_IS_NULL(10001,"存在为空的参数!"),

    PARAM_IS_NOT_NUMBER(10002,"存在应该为数字类型的参数!");



    private Integer code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
