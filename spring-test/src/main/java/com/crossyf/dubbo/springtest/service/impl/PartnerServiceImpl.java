package com.crossyf.dubbo.springtest.service.impl;

import com.crossyf.dubbo.springtest.entity.Partner;
import com.crossyf.dubbo.springtest.mapper.PartnerMapper;
import com.crossyf.dubbo.springtest.service.IPartnerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zuos
 * @since 2020-09-08
 */
@Service
public class PartnerServiceImpl implements IPartnerService {

    @Autowired
    private PartnerMapper pm;

    @Override
    public void insertGG(Partner po) {
        pm.insert(po);
    }
}
