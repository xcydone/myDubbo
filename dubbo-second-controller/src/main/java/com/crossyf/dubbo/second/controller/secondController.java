package com.crossyf.dubbo.second.controller;

import com.crossyf.dubbo.second.api.ISecondApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/second")
@Slf4j
public class secondController {
    @Reference
    ISecondApi ISecondApi;

    @RequestMapping("/saySecond")
    @ResponseBody
    public String saySecond(){
        return ISecondApi.saySecond("caifang");
    }
}
