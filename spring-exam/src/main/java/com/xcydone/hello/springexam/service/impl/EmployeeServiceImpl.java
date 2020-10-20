package com.xcydone.hello.springexam.service.impl;

import com.xcydone.hello.springexam.dto.EmployeeDto;
import com.xcydone.hello.springexam.entity.Employee;
import com.xcydone.hello.springexam.mapper.EmployeeMapper;
import com.xcydone.hello.springexam.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caif
 * @since 2020-10-20
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> qryEmployeeByName(String employeeName) {
        return employeeMapper.qryEmployeeByName(employeeName);
    }
}
