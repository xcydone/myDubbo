package com.crossyf.dubbo.second.controller;

import com.crossyf.dubbo.second.api.ISecondApi;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


@Api(tags = "second-Controller", value = "/second")
@RestController
@RequestMapping("/second/test")
@Slf4j
public class secondController {
    @Reference
    ISecondApi ISecondApi;

    @GetMapping("/saySecond")
    @ResponseBody
    public String saySecond(){
        return ISecondApi.saySecond("caifang");
    }
}
