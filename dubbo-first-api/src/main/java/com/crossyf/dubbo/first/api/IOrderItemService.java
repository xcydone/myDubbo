package com.crossyf.dubbo.first.api;

import com.crossyf.dubbo.first.dto.FileDto;
import com.crossyf.dubbo.first.dto.ItemDealDto;

public interface IOrderItemService {
    String itemDeal(ItemDealDto itemDealDto );

    void addFileIn(FileDto fileDto);
}
