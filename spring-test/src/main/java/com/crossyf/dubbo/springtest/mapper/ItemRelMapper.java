package com.crossyf.dubbo.springtest.mapper;

import com.crossyf.dubbo.springtest.dto.ItemRelDto;
import com.crossyf.dubbo.springtest.entity.ItemRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zuos
 * @since 2020-09-22
 */
public interface ItemRelMapper extends BaseMapper<ItemRel> {
    List<ItemRelDto> qryOperList(@Param("itemId") int itemId);
}
