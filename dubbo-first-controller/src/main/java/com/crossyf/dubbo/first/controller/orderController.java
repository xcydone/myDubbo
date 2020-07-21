package com.crossyf.dubbo.first.controller;

import cn.hutool.core.util.IdUtil;
import com.crossyf.dubbo.common.hbase.ReadHDFSDataToHbaseMR;
import com.crossyf.dubbo.common.utils.HBaseUtil;
import com.crossyf.dubbo.common.utils.MinioTemplate;
import com.crossyf.dubbo.first.api.IOrderItemService;
import com.crossyf.dubbo.first.dto.FileDto;
import com.crossyf.dubbo.first.dto.ItemDealDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.hadoop.util.ToolRunner;


@Api(tags = "工单管理-工单环节实例", value = "/order/itemDeal")
@Controller
@RequestMapping("/order")
@Slf4j
public class orderController {
    @Reference
    private IOrderItemService orderItemService;

    @Autowired(required = false)
    private MinioTemplate minioTemplate;

    @Autowired
    private HBaseUtil hBaseUtil;

    @ApiOperation(value = "工单环节处理")
    @PostMapping("/itemDeal")
    @ResponseBody
    public String itemDeal(@RequestBody ItemDealDto itemDealDto){
        return orderItemService.itemDeal(itemDealDto);
    }

    @ApiOperation(value = "上传文件接口")
    @PostMapping("/uploadFile")
    @ResponseBody
    public void uploadFile(@RequestParam("file")MultipartFile file){
        String uuid = IdUtil.simpleUUID();
        try {
            minioTemplate.putObject(uuid, file.getInputStream());
            // 文件内容写库
            FileDto fileDto = new FileDto();
            fileDto.setFileName(file.getOriginalFilename());
            fileDto.setFilePath(uuid);
            orderItemService.addFileIn(fileDto);
        }catch (Exception e){
            log.info("上传文件失败");
        }
    }

    @ApiOperation(value = "查询hbase的表")
    @GetMapping("/hbaseTableNames")
    @ResponseBody
    public TableName[] hbaseTableNames(){
        try {
            TableName[] tableNames = hBaseUtil.getAllTables();
            return tableNames;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @ApiOperation(value = "创建hbase的表")
    @GetMapping("/createTable")
    @ResponseBody
    public void createTable(String tableNameStr, String[] familys){
        try {
            hBaseUtil.createTable(tableNameStr, familys);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "读取hdfs上的txt到hbase的表")
    @GetMapping("/readHDFSDataToHbase")
    @ResponseBody
    public void readHDFSDataToHbase(String[] args){
        try {
            Configuration conf = hBaseUtil.getConf();
            int status = ToolRunner.run(conf, new ReadHDFSDataToHbaseMR(), args);
            System.exit(status);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
