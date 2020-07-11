package com.crossyf.dubbo.first.service.impl;

import cn.hutool.core.util.IdUtil;
import com.crossyf.dubbo.first.api.IOrderItemService;
import com.crossyf.dubbo.first.dto.FileDto;
import com.crossyf.dubbo.first.dto.ItemDealDto;
import com.crossyf.dubbo.first.entity.OrderFile;
import com.crossyf.dubbo.first.item.IOrderItemHandle;
import com.crossyf.dubbo.first.item.OrderItemContent;
import com.crossyf.dubbo.first.mapper.OrderFileMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class IOrderItemServiceImpl implements IOrderItemService {
    @Autowired
    private OrderItemContent orderItemContent;

    @Autowired
    private OrderFileMapper orderFileMapper;

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

    @Override
    public void addFileIn(FileDto fileDto) {
        OrderFile file = new OrderFile();
        BeanUtils.copyProperties(fileDto, file);
        String uuid = IdUtil.simpleUUID();
        file.setFileId(uuid);
        orderFileMapper.insert(file);
    }
}
