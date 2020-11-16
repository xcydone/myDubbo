package com.crossyf.dubbo.redis.api;

import com.crossyf.dubbo.redis.dto.User;

import java.util.List;

public interface IRedisService {
    boolean hasKey(Integer indexdb, String key);

    void delStringKey(int indexdb, String key);

    boolean setString(Integer indexdb, String key, Object value);

    Object getString(Integer indexdb, String key);

    boolean setList(Integer indexdb, String key, List<Object> value);

    List<Object> getList(Integer indexdb, String key, long start, long end);

    long getListSize(Integer indexdb, String key);
}
