package com.crossyf.dubbo.springtest.test.testListStream;

import lombok.Data;

@Data
public class OrderDto {
    private Integer orderId;

    /**
     * 流程ID
     */
    private Integer workflowId;

    /**
     * 工单编码
     */
    private String orderCode;

    /**
     * 工单名称
     */
    private String orderName;

    /**
     * 工单显示内容
     */
    private String orderComment;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }
}
