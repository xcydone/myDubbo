package com.crossyf.dubbo.first.controller;

import com.crossyf.dubbo.common.response.Result;
import com.crossyf.dubbo.common.utils.HBaseUtil;
import com.crossyf.dubbo.first.api.IFirstApi;
import com.crossyf.dubbo.first.dto.PersonDto;
import com.crossyf.dubbo.first.dto.PersonQryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "first-Controller", value = "/first")
@RestController
@RequestMapping("/first/test")
@Slf4j
public class firstController {
    @Reference
    private IFirstApi firstApi;

    @GetMapping("/sayFirst")
    @ResponseBody
    public Result sayFirst(){
        return Result.ok(firstApi.sayFirst("caifang"));
    }

    @ApiOperation(value = "通过id和name查询一个人", notes = "查询一个人")
    @GetMapping(value = "/findOne")
    public List<PersonDto> findOne(@RequestParam(value = "id") String id, @RequestParam(value = "username") String username){
        return firstApi.findOne(id, username);
    }

    @ApiOperation(value = "通过id查询一个人", notes = "查询一个人")
    @GetMapping(value = "/findPersonById")
    public PersonDto findPersonById(@RequestParam(value = "id") String id){
        return firstApi.findPersonById(id);
    }

    @ApiOperation(value = "通过id批量查询一个人", notes = "查询多个人")
    @GetMapping(value = "/findPersonByIds")
    public List<PersonDto> findPersonByIds(@RequestParam(value = "ids") List<String> ids){
        return firstApi.findPersonByIds(ids);
    }

    @ApiOperation(value = "通过sex和status查询一类人", notes = "查询多个人")
    @GetMapping(value = "/findPersonListBySex")
    public List<PersonDto> findPersonListBySex(@RequestParam(value = "sex") String sex,
                                               @RequestParam(value = "status") String status,
                                               @RequestParam(value = "fathers") List<String> fathers){
        return firstApi.findPersonListBySex(sex, status, fathers);
    }

    @ApiOperation(value = "通过条件map查询一类人", notes = "查询多个人")
    @PostMapping(value = "/findPersonListBySex")
    public List<PersonDto> findPersonListByMap(@RequestBody PersonQryDto personQryDto){
        return firstApi.findPersonListByMap(personQryDto);
    }

    @ApiOperation(value = "插入数据", notes = "插入单个数据")
    @PostMapping(value = "/inserPerson")
    public void inserPerson(@RequestBody PersonDto personDto){
        firstApi.inserPerson(personDto);
    }

    @ApiOperation(value = "批量插入数据", notes = "插入多个数据")
    @GetMapping(value = "/inserBatchPerson")
    public void inserBatchPerson(@RequestParam(value = "personDtos") List<PersonDto> personDtos){
        firstApi.inserBatchPerson(personDtos);
    }

    @ApiOperation(value = "删除数据", notes = "插入单个数据")
    @PostMapping(value = "/deletePerson")
    public void deletePerson(@RequestBody PersonQryDto personQryDto){
        firstApi.deletePerson(personQryDto);
    }
}
