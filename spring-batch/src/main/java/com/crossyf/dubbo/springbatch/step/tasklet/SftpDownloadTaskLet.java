package com.crossyf.dubbo.springbatch.step.tasklet;

import com.crossyf.dubbo.springbatch.ftp.FtpModle;
import com.crossyf.dubbo.springbatch.ftp.SFtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class SftpDownloadTaskLet implements Tasklet {

    private FtpModle ftpModle;
    private SFtpUtil fu;
    private Map<String, JobParameter> params;

    public SftpDownloadTaskLet(FtpModle ftpModle, SFtpUtil fu, Map<String, JobParameter> params) {
        this.ftpModle = ftpModle;
        this.fu = fu;
        this.params = params;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        // 下载文件
        if(fu.connect(ftpModle)){
            try{
                String partten = ftpModle.getPrefix();
                String parttenResult = this.matchFileName(partten);

                if(!"ERROR".equals(parttenResult)){
                    List<String> filesPath = fu.downLoadFileMatch(ftpModle.getSourceFilePath(), ftpModle.getTempFilePath(), ftpModle.getIsSingleFile(),
                            parttenResult);

                    ExecutionContext jobContext =chunkContext.getStepContext()
                            .getStepExecution()
                            .getJobExecution()
                            .getExecutionContext();
                    jobContext.put("filesPath", filesPath);
                    log.info("task下载文件完成");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            log.info("连接sftp失败");
        }

        return RepeatStatus.FINISHED;
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

        if (parttenSrc.contains("$") && parttenSrc.contains("{") && parttenSrc.contains("}")) {
            log.info("文件名称匹配参数配置错误");
            return "ERROR";
        }

        return parttenSrc;
    }

}
