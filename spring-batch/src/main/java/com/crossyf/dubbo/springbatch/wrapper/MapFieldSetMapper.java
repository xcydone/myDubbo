package com.crossyf.dubbo.springbatch.wrapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapFieldSetMapper extends BeanWrapperFieldSetMapper{

    public Map<String, Object> mapFieldSet(FieldSet fs) throws BindException {
        Map<String, Object> bean = new LinkedHashMap();
        for (int i=0; i < fs.getNames().length; i++) {
            bean.put(fs.getNames()[i], fs.getValues()[i]);
        }

        return bean;
    }

}
