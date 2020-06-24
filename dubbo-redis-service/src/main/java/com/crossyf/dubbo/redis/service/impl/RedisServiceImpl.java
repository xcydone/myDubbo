package com.crossyf.dubbo.redis.service.impl;

import com.crossyf.dubbo.common.utils.RedisUtil;
import com.crossyf.dubbo.redis.api.IRedisService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RedisServiceImpl implements IRedisService {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean hasKey(Integer indexdb, String key) {
        return redisUtil.hasKey(indexdb, key);
    }

    @Override
    public void delStringKey(int indexdb, String key) {
        redisUtil.del(indexdb, key);
    }

    @Override
    public boolean setString(Integer indexdb, String key, Object value) {
        return redisUtil.set(key,value, indexdb);
    }

    @Override
    public Object getString(Integer indexdb, String key) {
        return redisUtil.get(key, indexdb);
    }

    @Override
    public boolean setList(Integer indexdb, String key, List<Object> valueList) {
        return redisUtil.lSet(indexdb, key, valueList);
    }

    @Override
    public List<Object> getList(Integer indexdb, String key, long start, long end) {
        return redisUtil.lGet(indexdb, key, start, end);
    }

    @Override
    public long getListSize(Integer indexdb, String key) {
        return redisUtil.lGetListSize(indexdb, key);
    }
}
