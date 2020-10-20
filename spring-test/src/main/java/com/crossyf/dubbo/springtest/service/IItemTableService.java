package com.crossyf.dubbo.springtest.service;

import com.crossyf.dubbo.springtest.dto.ItemRelDto;
import com.crossyf.dubbo.springtest.dto.ItemTableDto;
import com.crossyf.dubbo.springtest.entity.ItemTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IItemTableService extends IService<ItemTable> {
    List<ItemTableDto> qryParentOperList(int itemId);
}
