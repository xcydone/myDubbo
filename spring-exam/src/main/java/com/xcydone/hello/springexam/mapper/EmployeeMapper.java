package com.xcydone.hello.springexam.mapper;

import com.xcydone.hello.springexam.dto.EmployeeDto;
import com.xcydone.hello.springexam.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caif
 * @since 2020-10-20
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    List<EmployeeDto> qryEmployeeByName(@Param("employeeName") String employeeName);
}
