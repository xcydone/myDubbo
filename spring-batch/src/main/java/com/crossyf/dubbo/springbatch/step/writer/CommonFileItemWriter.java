package com.crossyf.dubbo.springbatch.step.writer;

import com.crossyf.dubbo.springbatch.entity.ConfTableExecution;
import com.crossyf.dubbo.springbatch.service.IConfTableExecutionService;
import com.crossyf.dubbo.springbatch.wrapper.MapfieldExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.Map;
import java.util.UUID;

@Slf4j
public class CommonFileItemWriter extends FlatFileItemWriter<Map<String, Object>> {

    private IConfTableExecutionService confTableExecutionService;

    public CommonFileItemWriter(IConfTableExecutionService confTableExecutionService, String tableId) {
        this.confTableExecutionService = confTableExecutionService;
        this.init(tableId);
    }

    public CommonFileItemWriter(IConfTableExecutionService confTableExecutionService, String tableId, String filePath) {
        this.confTableExecutionService = confTableExecutionService;
        FileSystemResource fileSystemResource = new FileSystemResource(filePath + UUID.randomUUID().toString() + ".txt");
        this.setResource(fileSystemResource);
        this.init(tableId);
    }

    public void init(String tableId){
        ConfTableExecution confTable = confTableExecutionService.getConfTableExecutionMap(Integer.parseInt(tableId));
        if(confTable == null){
            log.info("请检查配置");
            return;
        }

        String columnsStr = confTable.getReaderColumnNames();
        String[] names = columnsStr.split(",");

        MapfieldExtractor mpfieldExtractor = new MapfieldExtractor();
        mpfieldExtractor.setNames(names);
        mpfieldExtractor.afterPropertiesSet();
        DelimitedLineAggregator lineAggregator = new DelimitedLineAggregator();
        lineAggregator.setDelimiter(confTable.getSeparation());
        lineAggregator.setFieldExtractor(mpfieldExtractor);
        setLineAggregator(lineAggregator);
    }
}
