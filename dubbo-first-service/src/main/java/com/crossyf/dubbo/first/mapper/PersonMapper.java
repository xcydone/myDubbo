package com.crossyf.dubbo.first.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crossyf.dubbo.first.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
    List<Person> findOne(@Param("id") String id, @Param("username") String username);
}
