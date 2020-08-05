package com.crossyf.dubbo.first.api;

import com.crossyf.dubbo.common.zookeeper.event.Event;

public interface IEventService {
    void dispatcher(Event event) throws Exception;
}
