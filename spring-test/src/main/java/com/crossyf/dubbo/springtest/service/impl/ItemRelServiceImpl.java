package com.crossyf.dubbo.springtest.service.impl;

import com.crossyf.dubbo.springtest.dto.ItemRelDto;
import com.crossyf.dubbo.springtest.entity.ItemRel;
import com.crossyf.dubbo.springtest.mapper.ItemRelMapper;
import com.crossyf.dubbo.springtest.service.IItemRelService;
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
public class ItemRelServiceImpl extends ServiceImpl<ItemRelMapper, ItemRel> implements IItemRelService {

    @Autowired
    private ItemRelMapper itemRelMapper;

    @Override
    public List<ItemRelDto> qryOperList(int itemId) {
        return itemRelMapper.qryOperList(itemId);
    }
}
