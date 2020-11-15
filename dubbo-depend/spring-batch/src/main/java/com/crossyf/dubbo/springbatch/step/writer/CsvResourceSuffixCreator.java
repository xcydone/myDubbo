package com.crossyf.dubbo.springbatch.step.writer;

import com.crossyf.dubbo.springbatch.constant.BatchGlobal;
import org.springframework.batch.item.file.ResourceSuffixCreator;

public class CsvResourceSuffixCreator implements ResourceSuffixCreator {

    private String suffix;

    public CsvResourceSuffixCreator(String suffix) {
        this.suffix = suffix == null ? BatchGlobal.DEFAULT_TXT : suffix;
    }

    @Override
    public String getSuffix(int i) {
        return i + "." + suffix ;
    }
}