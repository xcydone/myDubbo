package com.crossyf.dubbo.second.controller;

import com.crossyf.dubbo.second.api.IEventService;
import com.crossyf.dubbo.second.api.ISecondApi;
import com.crossyf.dubbo.second.event.RuleEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/second")
@Slf4j
public class secondController {
    @Reference
    ISecondApi ISecondApi;

    @Reference
    private IEventService eventService;

    @RequestMapping("/saySecond")
    @ResponseBody
    public String saySecond(){
        return ISecondApi.saySecond("caifang");
    }

    @RequestMapping("/deleteCache")
    @ResponseBody
    public String deleteCache() {
        Integer adtId = 9902;
        try {
            eventService.dispatcher(new RuleEvent(adtId));
            return "更新成功！";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("", e.getMessage());
            return "更新失败！";
        }
    }
}
