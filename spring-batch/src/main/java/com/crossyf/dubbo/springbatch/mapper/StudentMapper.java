package com.crossyf.dubbo.springbatch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crossyf.dubbo.springbatch.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper {
    void insertBatch(@Param("list") List<Student> studentList);
}
