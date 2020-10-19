package com.crossyf.dubbo.springtest.service.impl;

import com.crossyf.dubbo.springtest.dto.ItemRelDto;
import com.crossyf.dubbo.springtest.dto.ItemTableDto;
import com.crossyf.dubbo.springtest.entity.ItemTable;
import com.crossyf.dubbo.springtest.mapper.ItemRelMapper;
import com.crossyf.dubbo.springtest.mapper.ItemTableMapper;
import com.crossyf.dubbo.springtest.service.IItemTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zuos
 * @since 2020-09-22
 */
@Service
public class ItemTableServiceImpl extends ServiceImpl<ItemTableMapper, ItemTable> implements IItemTableService {

    @Autowired
    private ItemTableMapper itemTableMapper;

    @Override
    public List<ItemTableDto> qryParentOperList(int itemId) {
        return itemTableMapper.qryParentOperList(itemId);
    }
}
