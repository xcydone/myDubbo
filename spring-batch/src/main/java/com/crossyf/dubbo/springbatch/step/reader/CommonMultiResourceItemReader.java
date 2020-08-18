package com.crossyf.dubbo.springbatch.step.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonMultiResourceItemReader extends MultiResourceItemReader<Map<String, Object>> {

    private List<String> filesPath;

    public CommonMultiResourceItemReader(FlatFileItemReader commonFileItemReader, List<String> filesPath) {
        this.filesPath = filesPath;
        this.setResources(getResource());
        this.setDelegate(commonFileItemReader);
    }

    public CommonMultiResourceItemReader(FlatFileItemReader commonFileItemReader, String fileParentPath) {
        this.filesPath = getAllFilesPath(fileParentPath);
        this.setResources(getResource());
        this.setDelegate(commonFileItemReader);
    }

    private Resource[] getResource() {
        List<Resource> resourceList = new ArrayList<>();
        if (filesPath != null) {
            for (String resource : filesPath) {
                File file = new File(resource);
                if (!file.isDirectory()) {
                    FileSystemResource fileSystemResource = new FileSystemResource(resource);
                    resourceList.add(fileSystemResource);
                }
            }
        }
        Resource[] resources = new Resource[resourceList.size()];
        return resourceList.toArray(resources);
    }

    private List<String> getAllFilesPath(String fileParentPath){
        List<String> allFilesPath = new ArrayList<>();

        File file = new File(fileParentPath);
        if (file.isDirectory()) {
            File[] fileChildrens = file.listFiles();
            if (fileChildrens != null) {
                for (File children : fileChildrens) {
                    if (!children.isDirectory()) {
                        allFilesPath.add(children.getAbsolutePath());
                    }
                }
            }
        }
        return allFilesPath;
    }
}
