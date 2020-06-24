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
@Order(OrderConstant.ORDER_STEPTHREE)
@OperType(value = OrderConstant.ORDER_STEPTHREE)
public class OrderItemStepThreeImpl implements IOrderItemHandle {
    @Override
    public String handle(Object o) {
        return "环节3处理成功";
    }

    @Override
    public Object transFormDto(ItemDealDto itemDealDto) {
        return null;
    }
}
