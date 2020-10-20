package com.crossyf.dubbo.springtest.service;

import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.dto.PartnerQryDto;
import com.crossyf.dubbo.springtest.entity.Partner;

import java.util.List;
import java.util.Map;

public interface IPartnerSimpleService {
    List<PartnerDto> findPartnerByParam(String name, String levelName);
}
