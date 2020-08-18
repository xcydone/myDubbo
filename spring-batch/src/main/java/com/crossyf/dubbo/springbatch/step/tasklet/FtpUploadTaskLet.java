package com.crossyf.dubbo.springbatch.step.tasklet;

import com.crossyf.dubbo.springbatch.ftp.FtpModle;
import com.crossyf.dubbo.springbatch.ftp.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class FtpUploadTaskLet implements Tasklet {

    private FtpModle ftpModle;
    private FtpUtil fu;
    private String localPath;

    public FtpUploadTaskLet(FtpModle ftpModle, FtpUtil fu) {
        this.ftpModle = ftpModle;
        this.fu = fu;
        this.localPath = ftpModle.getTempFilePath();
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        // 上传文件
        if(fu.connect(ftpModle)){
            try{
                fu.uploadFile(ftpModle.getSourceFilePath(), localPath);
                log.info("task上传文件完成");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            log.info("连接ftp失败");
        }

        return RepeatStatus.FINISHED;
    }
}
