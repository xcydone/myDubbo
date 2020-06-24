package com.crossyf.dubbo.first.item;

import com.crossyf.dubbo.first.dto.ItemDealDto;

public interface IOrderItemHandle<T> {

    /**
     * 环节处理器，不同的步骤重写不同的业务操作
     * @param t
     * @return
     */
    String handle(T t);

    /**
     * 入参转换
     * @param itemDealDto
     * @return
     */
    T transFormDto(ItemDealDto itemDealDto);
}
