package com.crossyf.dubbo.springbatch.step.writer;

import com.crossyf.dubbo.springbatch.entity.ConfTableExecution;
import com.crossyf.dubbo.springbatch.ftp.FtpModle;
import com.crossyf.dubbo.springbatch.service.IConfTableExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CommonMultiResourceItemWriter extends MultiResourceItemWriter<Map<String, Object>> {

    private IConfTableExecutionService confTableExecutionService;
    private Map<String, JobParameter> params;
    private String tableId;
    private String fileNameModel;
    private String suffixStr;

    public CommonMultiResourceItemWriter(FtpModle ftpModle, Map<String, JobParameter> params,
                                         IConfTableExecutionService confTableExecutionService,
                                         FlatFileItemWriter commonFileItemWriter) {
        this.confTableExecutionService = confTableExecutionService;
        this.params = params;
        this.tableId = params.get("tableId").toString();
        this.fileNameModel = this.getFileNameModel();
        if("ERROR".equals(this.fileNameModel)){
            return;
        }

        this.setDelegate(commonFileItemWriter);

        if(params.get("size") != null){
            int size = Integer.parseInt(params.get("size").toString());
            this.setItemCountLimitPerResource(size);
        }

        this.setResource(getResource(ftpModle.getTempFilePath()));

        // 设置文件格式
        this.setResourceSuffixCreator(new CsvResourceSuffixCreator(suffixStr));
    }

    private Resource getResource(String localPath) {
        SimpleDateFormat df2 = new SimpleDateFormat("HHmmss");
        FileSystemResource fileSystemResource = new FileSystemResource(localPath + fileNameModel + df2.format(new Date()));
        return fileSystemResource;
    }

    private String getFileNameModel(){

        ConfTableExecution cofTable = confTableExecutionService.getConfTableExecutionMap(Integer.parseInt(tableId));
        String writerFileName = cofTable.getWriterFileName();

        //没有配置 名称 + 格式
        if(writerFileName == null){
            return "";
        }

        String[] names = writerFileName.split("\\.");
        String writerFileNameMatch = null;

        //没有配置文件格式
        if(names.length == 1){
            writerFileNameMatch = this.matchFileName(names[0]);
        }

        // 名称 + 格式
        if(names.length > 1){
            String str = writerFileName.substring(0, writerFileName.length() - names[names.length-1].length() - 1);
            writerFileNameMatch = this.matchFileName(str);
            this.suffixStr = names[names.length-1];
        }

        return writerFileNameMatch;
    }

    public String matchFileName(String partten){
        String parttenSrc = partten;
        //替换条件中的通配符
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            if (parttenSrc.contains(key)) {
                if(key.contains("$")){
                    char[] chars = key.toCharArray();
                    StringBuilder keySb = new StringBuilder();
                    for (char c: chars){
                        if(c == '$'){
                            keySb.append("\\$");
                        }else if (c == '{'){
                            keySb.append("\\{");
                        }else if(c == '}'){
                            keySb.append("\\}");
                        }else{
                            keySb.append(c);
                        }
                    }
                    parttenSrc = parttenSrc.replaceAll(keySb.toString(), params.get(key).toString());
                }
            }
        }
        if(parttenSrc.indexOf("${yyyymmdd}") != -1 && params.get("${yyyymmdd}") == null){  // 当天时间
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            parttenSrc = parttenSrc.replace("${yyyymmdd}", df.format(new Date()));
        }
        log.info("reader prepared execute sql -> " + parttenSrc);

        if (parttenSrc.contains("$") || parttenSrc.contains("{") || parttenSrc.contains("}")) {
            log.info("文件名称匹配参数配置错误");
            return "ERROR";
        }
        return parttenSrc;
    }
}
