package com.crossyf.dubbo.springtest.service;

import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.dto.PartnerQryDto;
import com.crossyf.dubbo.springtest.entity.Partner;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPartnerService{
    void insertGG(Partner po);

    List<PartnerDto> findPartnerByParam(String name, String levelName);

    List<PartnerDto> findPartnerByEntity(PartnerQryDto qryDto);

    List<PartnerDto> findPartnerTwoOne(PartnerQryDto qryDto);

    void updatePartnerById(PartnerQryDto qryDto);

    List<PartnerDto> findPartnerInStatus(List status);

    List<PartnerDto> findPartnerByLang(String name, String levelName);

    List<PartnerDto> findPartnerByScript(String name, String levelName);

    Map<String, Object> findPartnerMap(int id);

    List<PartnerDto> findPartnerStatus(int status);

    Map selectDTableId(int id);

}
