package com.crossyf.dubbo.first.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UploadFileDto implements Serializable {
    private static final long serialVersionUID = -5206700426366031474L;
    private String operId;
    private LocalDate operDate;
}
