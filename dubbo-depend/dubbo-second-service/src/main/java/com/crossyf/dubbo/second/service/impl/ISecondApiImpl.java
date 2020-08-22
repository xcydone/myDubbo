package com.crossyf.dubbo.second.service.impl;

import com.crossyf.dubbo.second.api.ISecondApi;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class ISecondApiImpl implements ISecondApi {
    @Override
    public String saySecond(String name) {
        return "Second," + name;
    }
}
