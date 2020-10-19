package com.crossyf.dubbo.springtest.service;

import com.crossyf.dubbo.springtest.dto.ItemRelDto;
import com.crossyf.dubbo.springtest.entity.ItemRel;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zuos
 * @since 2020-09-22
 */
public interface IItemRelService extends IService<ItemRel> {
    List<ItemRelDto> qryOperList(int itemId);
}
