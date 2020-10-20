package com.crossyf.dubbo.springtest.service;

import com.crossyf.dubbo.springtest.dto.ItemRelDto;
import com.crossyf.dubbo.springtest.entity.ItemRel;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IItemRelService extends IService<ItemRel> {
    List<ItemRelDto> qryOperList(int itemId);
}
