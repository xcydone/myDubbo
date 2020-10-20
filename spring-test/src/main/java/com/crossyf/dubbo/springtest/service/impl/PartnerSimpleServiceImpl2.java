package com.crossyf.dubbo.springtest.service.impl;

import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.service.IPartnerSimpleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("PartnerSimpleServiceImpl2")
public class PartnerSimpleServiceImpl2 implements IPartnerSimpleService {
    @Override
    public List<PartnerDto> findPartnerByParam(String name, String levelName) {

        // 反射练习

        return null;
    }
}
