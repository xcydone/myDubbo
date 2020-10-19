package com.crossyf.dubbo.springtest.service;

import com.crossyf.dubbo.springtest.dto.ItemRelDto;
import com.crossyf.dubbo.springtest.dto.ItemTableDto;
import com.crossyf.dubbo.springtest.entity.ItemTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zuos
 * @since 2020-09-22
 */
public interface IItemTableService extends IService<ItemTable> {
    List<ItemTableDto> qryParentOperList(int itemId);
}
