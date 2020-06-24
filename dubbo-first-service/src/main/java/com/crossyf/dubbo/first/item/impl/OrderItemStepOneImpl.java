package com.crossyf.dubbo.first.item.impl;

import com.crossyf.dubbo.common.annotation.OperType;
import com.crossyf.dubbo.common.constant.OrderConstant;
import com.crossyf.dubbo.first.item.IOrderItemHandle;
import com.crossyf.dubbo.first.dto.ItemDealDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(OrderConstant.ORDER_STEPONE)
@OperType(value = OrderConstant.ORDER_STEPONE)
public class OrderItemStepOneImpl implements IOrderItemHandle {
    @Override
    public String handle(Object o) {
        return "环节1处理成功";
    }

    @Override
    public Object transFormDto(ItemDealDto itemDealDto) {
        return null;
    }
}
