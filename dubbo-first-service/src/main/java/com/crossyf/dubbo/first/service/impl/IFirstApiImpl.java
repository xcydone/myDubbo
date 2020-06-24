package com.crossyf.dubbo.first.service.impl;

import com.crossyf.dubbo.first.api.IFirstApi;
import org.apache.dubbo.config.annotation.Service;

@Service
public class IFirstApiImpl implements IFirstApi {
    @Override
    public String sayFirst(String name) {
        return "First," + name;
    }
}
