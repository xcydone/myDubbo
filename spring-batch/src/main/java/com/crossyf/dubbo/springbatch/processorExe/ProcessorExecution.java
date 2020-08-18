package com.crossyf.dubbo.springbatch.processorExe;

import com.crossyf.dubbo.springbatch.entity.ConfTableExecution;

import java.util.Map;

public interface ProcessorExecution {

    Map<String, Object> processor(Map<String, Object> dataInputMap,  ConfTableExecution confTable);
}
