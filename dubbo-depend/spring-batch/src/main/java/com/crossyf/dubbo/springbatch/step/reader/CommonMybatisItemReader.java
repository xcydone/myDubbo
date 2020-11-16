package com.crossyf.dubbo.springbatch.step.reader;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;

import java.util.HashMap;
import java.util.Map;

public class CommonMybatisItemReader<T> extends MyBatisPagingItemReader<T> {

    public CommonMybatisItemReader(SqlSessionFactory sqlSessionFactory, String queryId, Map<String, Object> paramValues, int pageSize) {
        setSqlSessionFactory(sqlSessionFactory);
        setQueryId(queryId);
        setParameterValues(paramValues);
        setPageSize(pageSize);
    }
}