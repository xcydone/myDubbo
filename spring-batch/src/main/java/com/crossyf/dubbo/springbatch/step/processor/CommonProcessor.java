package com.crossyf.dubbo.springbatch.step.processor;

import com.crossyf.dubbo.springbatch.entity.ConfTableExecution;
import com.crossyf.dubbo.springbatch.service.IConfTableExecutionService;
import com.crossyf.dubbo.springbatch.util.SpringUtil;
import com.crossyf.dubbo.springbatch.processorExe.ProcessorExecution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;

@Slf4j
public class CommonProcessor implements ItemProcessor<Map<String, Object>, Map<String, Object>> {

    private SpringUtil springUtil;
    private IConfTableExecutionService confTableExecutionService;
    private String tableId;

    public CommonProcessor(String tableId, IConfTableExecutionService confTableExecutionService, SpringUtil springUtil) {
        this.tableId = tableId;
        this.confTableExecutionService = confTableExecutionService;
        this.springUtil = springUtil;
    }

    @Override
    public Map<String, Object> process(Map<String, Object> dataInputMap) throws Exception {
        ConfTableExecution confTable = confTableExecutionService.getConfTableExecutionMap(Integer.parseInt(tableId));
        if(confTable == null){
            log.info("请检查配置");
            return null;
        }

        String className = confTable.getProcessorExecutionClass();
        Class clz = Class.forName(className);
        ProcessorExecution pn = (ProcessorExecution) springUtil.getBean(clz);
        return pn.processor(dataInputMap, confTable);
    }
}
