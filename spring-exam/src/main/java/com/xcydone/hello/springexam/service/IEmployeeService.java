package com.xcydone.hello.springexam.service;

import com.xcydone.hello.springexam.dto.EmployeeDto;
import com.xcydone.hello.springexam.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caif
 * @since 2020-10-20
 */
public interface IEmployeeService extends IService<Employee> {

    List<EmployeeDto> qryEmployeeByName(String employeeName);
}
