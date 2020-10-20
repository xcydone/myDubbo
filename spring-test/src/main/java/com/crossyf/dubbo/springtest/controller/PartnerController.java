package com.crossyf.dubbo.springtest.controller;

import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.dto.StringDto;
import com.crossyf.dubbo.springtest.service.IPartnerService;
import com.crossyf.dubbo.springtest.service.IPartnerSimpleService;
import com.crossyf.dubbo.springtest.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partner")
public class PartnerController {
    @Autowired
    private IPartnerService partnerService;

    @Autowired
    @Qualifier("PartnerSimpleServiceImpl1")
    private IPartnerSimpleService partnerSimpleService;

    @GetMapping(value = "/findPartnerByParam")
    public List<PartnerDto> findPartnerByParam(String name, String levelName){

        List<PartnerDto> li = partnerService.findPartnerByParam(name,levelName);
        return li;
    }

}
