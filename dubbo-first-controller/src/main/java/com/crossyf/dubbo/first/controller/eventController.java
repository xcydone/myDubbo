package com.crossyf.dubbo.first.controller;

import com.crossyf.dubbo.first.api.IEventService;
import com.crossyf.dubbo.first.event.RuleEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@Slf4j
public class eventController {

    @Reference
    private IEventService eventService;

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
