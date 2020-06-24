package com.crossyf.dubbo.first.controller;

import com.crossyf.dubbo.first.api.IFirstApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class firstController {
    @Reference
    private IFirstApi firstApi;

    @GetMapping("/sayFirst")
    @ResponseBody
    public String sayFirst(){
        return firstApi.sayFirst("caifang");
    }
}
