package com.crossyf.dubbo.springtest.service.impl;

import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.mapper.PartnerMapper;
import com.crossyf.dubbo.springtest.service.IPartnerSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PartnerSimpleServiceImpl1")
public class PartnerSimpleServiceImpl1 implements IPartnerSimpleService {

    @Autowired
    private PartnerMapper partnerMapper;

    @Override
    public List<PartnerDto> findPartnerByParam(String name, String levelName) {
        return partnerMapper.findPartnerByParam(name, levelName);
    }
}
