package com.crossyf.dubbo.second.service.impl;

import com.crossyf.dubbo.common.zookeeper.event.Event;
import com.crossyf.dubbo.common.zookeeper.manager.EventManager;
import com.crossyf.dubbo.second.api.IEventService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    EventManager eventManager;

    @Override
    public void dispatcher(Event event) throws Exception {
        eventManager.dispatcher(event);
    }
}
