package com.crossyf.dubbo.springtest.controller;

import com.alibaba.fastjson.JSONObject;
import com.crossyf.dubbo.springtest.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("prototype")
public class LoginController{

    @Autowired
    private ILoginService loginService;

    @PostMapping(value = "/login")
    public JSONObject login(@RequestParam(value = "sign") String sign,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "data") String data
    ) throws Exception {
        return loginService.login(data);
    }
}
