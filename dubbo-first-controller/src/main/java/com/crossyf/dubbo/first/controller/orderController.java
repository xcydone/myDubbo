package com.crossyf.dubbo.first.controller;

import cn.hutool.core.util.IdUtil;
import com.crossyf.dubbo.common.utils.MinioTemplate;
import com.crossyf.dubbo.first.api.IOrderItemService;
import com.crossyf.dubbo.first.dto.ItemDealDto;
import com.crossyf.dubbo.first.dto.UploadFileDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Api(tags = "工单管理-工单环节实例", value = "/order/itemDeal")
@Controller
@RequestMapping("/order")
@Slf4j
public class orderController {
    @Reference
    private IOrderItemService orderItemService;

    @Autowired(required = false)
    private MinioTemplate minioTemplate;

    @ApiOperation(value = "工单环节处理")
    @PostMapping("/itemDeal")
    @ResponseBody
    public String itemDeal(@RequestBody ItemDealDto itemDealDto){
        return orderItemService.itemDeal(itemDealDto);
    }

    @ApiOperation(value = "上传文件接口")
    @PostMapping("/uploadFile")
    @ResponseBody
    public void uploadFile(MultipartFile file){
        String uuid = IdUtil.simpleUUID();
        try {
            minioTemplate.putObject(uuid, file.getInputStream());
            // 文件内容写库
        }catch (Exception e){
            log.info("上传文件失败");
        }
    }
}
