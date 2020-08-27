package com.crossyf.dubbo.springtest.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.crossyf.dubbo.springtest.service.ILoginService;
import com.crossyf.dubbo.springtest.utils.JSONUtils;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
    @Override
    public JSONObject login(Object data) {
        JSONObject responseParams = new JSONObject();

        JSONObject requestParams = JSONObject.parseObject(data.toString());
        if (JSONUtils.getJSONString(requestParams, "username").equalsIgnoreCase(
                JSONUtils.getJSONString(requestParams, "password"))) {
            responseParams.put("code", "1000");
            responseParams.put("message", "SUCCESS");
        } else {
            responseParams.put("code", "2000");
            responseParams.put("message", "FAIL");
        }

        return responseParams;
    }
}
