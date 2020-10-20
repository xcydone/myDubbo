package com.crossyf.dubbo.springtest.controller;

import com.crossyf.dubbo.springtest.dto.StringDto;
import com.crossyf.dubbo.springtest.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ITestService testService;

    @GetMapping(value = "/getMsg")
    public StringDto getMsg(){
       return testService.getMsg();
    }

}
