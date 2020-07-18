package com.crossyf.dubbo.first.controller;

import com.crossyf.dubbo.common.response.Result;
import com.crossyf.dubbo.common.utils.HBaseUtil;
import com.crossyf.dubbo.first.api.IFirstApi;
import com.crossyf.dubbo.first.dto.PersonDto;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/first")
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
        log.info("查询一个人的角色信息");
        return firstApi.findOne(id, username);
    }
}
