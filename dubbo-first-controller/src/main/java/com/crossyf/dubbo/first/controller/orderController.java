package com.crossyf.dubbo.first.controller;

import com.crossyf.dubbo.first.api.IOrderItemService;
import com.crossyf.dubbo.first.dto.ItemDealDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Api(tags = "工单管理-工单环节实例", value = "/order/itemDeal")
@Controller
@RequestMapping("/order")
public class orderController {
    @Reference
    IOrderItemService orderItemService;

    @ApiOperation(value = "工单环节处理")
    @PostMapping("/itemDeal")
    @ResponseBody
    public String itemDeal(@RequestBody ItemDealDto itemDealDto){
        return orderItemService.itemDeal(itemDealDto);
    }
}
