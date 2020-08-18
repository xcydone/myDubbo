package com.crossyf.dubbo.springbatch.wrapper;

import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.*;

public class MapfieldExtractor implements FieldExtractor<Map<String, Object>>, InitializingBean {

    private String[] names;

    public MapfieldExtractor() {
    }

    public void setNames(String[] names) {
        Assert.notNull(names, "Names must be non-null");
        this.names = (String[])Arrays.asList(names).toArray(new String[names.length]);
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.names, "The 'names' property must be set.");
    }


    @Override
    public Object[] extract(Map<String, Object> item) {
        List<Object> values = new ArrayList();
        for(String key: item.keySet()){
            values.add(item.get(key));
        }
        return values.toArray();
    }
}
