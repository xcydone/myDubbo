package com.crossyf.dubbo.springbatch.step.reader;

import com.crossyf.dubbo.springbatch.entity.ConfTableExecution;
import com.crossyf.dubbo.springbatch.service.IConfTableExecutionService;
import com.crossyf.dubbo.springbatch.wrapper.MapFieldSetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CommonFileItemReader extends FlatFileItemReader<Map<String, Object>> {
    private IConfTableExecutionService confTableExecutionService;

    public CommonFileItemReader(IConfTableExecutionService iConfTableExecutionService, String tableId) {
        this.confTableExecutionService = iConfTableExecutionService;
        this.init(tableId);
    }

    public CommonFileItemReader(IConfTableExecutionService iConfTableExecutionService, String tableId, String filePath) {
        this.confTableExecutionService = iConfTableExecutionService;
        Resource resource = new FileSystemResource(filePath);
        this.setResource(resource);
        this.init(tableId);
    }

    private void init(String tableId) {
        ConfTableExecution confTable = confTableExecutionService.getConfTableExecutionMap(Integer.parseInt(tableId));
        if(confTable == null){
            log.info("请检查配置");
            return;
        }

        String columnsStr = confTable.getReaderColumnNames();
        String[] names = columnsStr.split(",");

        DefaultLineMapper defaultLineMapper = new DefaultLineMapper();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setFieldSetFactory(new DefaultFieldSetFactory());
        delimitedLineTokenizer.setNames(names);
        delimitedLineTokenizer.setDelimiter(confTable.getSeparation());
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        MapFieldSetMapper fieldSetMapper = new MapFieldSetMapper();
        fieldSetMapper.setTargetType(HashMap.class);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        setLineMapper(defaultLineMapper);
    }

}
