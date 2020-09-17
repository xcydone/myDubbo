package com.crossyf.dubbo.springtest.service.impl;

import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.dto.PartnerQryDto;
import com.crossyf.dubbo.springtest.entity.Partner;
import com.crossyf.dubbo.springtest.mapper.PartnerMapper;
import com.crossyf.dubbo.springtest.service.IPartnerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<PartnerDto> findPartnerByParam(String name, String levelName) {
        return pm.findPartnerByParam(name, levelName);
    }

    @Override
    public List<PartnerDto> findPartnerByEntity(PartnerQryDto qryDto) {
        return pm.findPartnerByEntity(qryDto);
    }

    @Override
    public List<PartnerDto> findPartnerTwoOne(PartnerQryDto qryDto) {
        return pm.findPartnerTwoOne(qryDto);
    }

    @Override
    public void updatePartnerById(PartnerQryDto qryDto){
        pm.updatePartnerById(qryDto);
    }

    @Override
    public List<PartnerDto> findPartnerInStatus(List status){
        return pm.findPartnerInStatus(status);
    }

    @Override
    public List<PartnerDto> findPartnerByLang(String name, String levelName) {
        return pm.findPartnerByLang(name, levelName);
    }

    @Override
    public List<PartnerDto> findPartnerByScript(String name, String levelName) {
        return pm.findPartnerByScript(name, levelName);
    }

    @Override
    public Map<String, Object> findPartnerMap(int id) {
        return pm.findPartnerMap(id);
    }

    @Override
    public List<PartnerDto> findPartnerStatus(int status) {
       return pm.findPartnerStatus(status);
    }
}
