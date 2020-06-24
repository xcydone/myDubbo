package com.crossyf.dubbo.first.service.impl;

import com.crossyf.dubbo.first.api.IOrderItemService;
import com.crossyf.dubbo.first.dto.ItemDealDto;
import com.crossyf.dubbo.first.item.IOrderItemHandle;
import com.crossyf.dubbo.first.item.OrderItemContent;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class IOrderItemServiceImpl implements IOrderItemService {
    @Autowired
    private OrderItemContent orderItemContent;

    @Override
    public String itemDeal(ItemDealDto itemDealDto){
        Integer perType = itemDealDto.getItemOperType();
        try {
            IOrderItemHandle orderItemHandle = orderItemContent.getInstance(perType);
            return orderItemHandle.handle(itemDealDto);
        }catch (Exception e){
            return "";
        }
    }
}
