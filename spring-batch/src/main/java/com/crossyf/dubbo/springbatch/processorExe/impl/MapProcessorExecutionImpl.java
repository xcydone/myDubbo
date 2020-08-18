package com.crossyf.dubbo.springbatch.processorExe.impl;

import com.crossyf.dubbo.springbatch.entity.ConfTableExecution;
import com.crossyf.dubbo.springbatch.processorExe.ProcessorExecution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Slf4j
public class MapProcessorExecutionImpl implements ProcessorExecution {

    @Override
    public Map<String, Object> processor(Map<String, Object> dataInputMap, ConfTableExecution confTable) {

        // key -- 字段名   value -- 字段值
        Map<String, Object> dataOutPutMap = new LinkedHashMap();
        if(dataInputMap != null && dataInputMap.size() > 0){
            String dataId = confTable.getIdColumnName();
            if(dataInputMap.containsKey(dataId)){
                dataInputMap.remove(dataId);
            }

            for(String key : dataInputMap.keySet()){
                dataOutPutMap.put(key, dataInputMap.get(key));
            }
        }
        return dataOutPutMap;
    }
}
