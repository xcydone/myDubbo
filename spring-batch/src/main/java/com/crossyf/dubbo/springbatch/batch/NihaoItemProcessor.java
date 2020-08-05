package com.crossyf.dubbo.springbatch.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class NihaoItemProcessor implements ItemProcessor<String, String> {
    // 快速创建日志实例
    private static final Logger LOGGER = LoggerFactory.getLogger(NihaoItemProcessor.class);

    @Override
    public String process(String str) throws Exception {
        String greeting = "nihao " + str + "!";

        LOGGER.info("converting '{}' into '{}'", str, greeting);
        return greeting;
    }
}
