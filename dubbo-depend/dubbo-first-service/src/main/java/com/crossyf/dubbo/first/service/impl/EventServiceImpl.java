package com.crossyf.dubbo.first.service.impl;

import com.crossyf.dubbo.common.zookeeper.event.Event;
import com.crossyf.dubbo.common.zookeeper.manager.EventManager;
import com.crossyf.dubbo.first.api.IEventService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class EventServiceImpl implements IEventService {
    @Autowired
    EventManager eventManager;

    @Override
    public void dispatcher(Event event) throws Exception {
        eventManager.dispatcher(event);
    }
}
