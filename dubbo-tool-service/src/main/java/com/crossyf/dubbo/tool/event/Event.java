package com.crossyf.dubbo.tool.event;

import java.io.Serializable;

/**
 * Zookeeper事件类
 * @author zuos
 * @date 2020/4/3 11:09 上午
 */
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private long timestamp;
    private String id;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
