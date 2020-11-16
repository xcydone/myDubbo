package com.crossyf.dubbo.first.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description= "返回参数定义")
public class FileDto implements Serializable {
    private static final long serialVersionUID = -1929819976249340724L;

    private String fileId;

    private Integer orderId;

    private Integer handleId;

    private String fileName;

    private String filePath;

    private Integer uploadStaff;

    private LocalDateTime uploadDate;
}
