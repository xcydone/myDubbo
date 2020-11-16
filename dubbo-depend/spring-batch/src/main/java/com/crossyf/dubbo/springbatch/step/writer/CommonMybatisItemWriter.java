package com.crossyf.dubbo.springbatch.step.writer;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;

/**
 * @author wh_Leimc
 * @date 2020/8/8 16:58
 */
public class CommonMybatisItemWriter<T> extends MyBatisBatchItemWriter<T> {
    public CommonMybatisItemWriter(SqlSessionFactory sqlSessionFactory, String statementId){
        setSqlSessionTemplate(new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH));
        setStatementId(statementId);
    }
}
