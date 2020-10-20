package com.crossyf.dubbo.springtest.mapper;

import com.crossyf.dubbo.springtest.dto.ItemRelDto;
import com.crossyf.dubbo.springtest.dto.ItemTableDto;
import com.crossyf.dubbo.springtest.entity.ItemTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemTableMapper extends BaseMapper<ItemTable> {
    List<ItemTableDto> qryParentOperList(@Param("itemId") int itemId);
}
