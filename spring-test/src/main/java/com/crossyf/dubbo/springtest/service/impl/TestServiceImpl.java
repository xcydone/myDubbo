package com.crossyf.dubbo.springtest.service.impl;

import com.crossyf.dubbo.springtest.dto.StringDto;
import com.crossyf.dubbo.springtest.service.ITestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {
    @Override
    public StringDto getMsg() {
        StringDto strDto = new StringDto();
        strDto.setStr1("nihao");
        strDto.setStr2("hello");
        return strDto;
    }
}
