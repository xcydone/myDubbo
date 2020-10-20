package com.xcydone.hello.springexam.controller;


import com.xcydone.hello.springexam.dto.EmployeeDto;
import com.xcydone.hello.springexam.response.Result;
import com.xcydone.hello.springexam.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caif
 * @since 2020-10-20
 */
@Api(tags = "员工管理", value = "/v1/employee")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @ApiOperation(value = "通过用户名获取用户")
    @GetMapping("/qryEmployeeByName")
    public Result<List<EmployeeDto>> qryEmployeeByName(String employeeName){
        List<EmployeeDto> dtos = employeeService.qryEmployeeByName(employeeName);
        return Result.ok(dtos);
    }

}

