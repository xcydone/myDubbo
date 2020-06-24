package com.crossyf.dubbo.redis.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Setter
@Getter
public class User implements Serializable {
    private static final long serialVersionUID = 9078873892666009182L;

    private String id;

    private String name;

    private Integer age;
}
