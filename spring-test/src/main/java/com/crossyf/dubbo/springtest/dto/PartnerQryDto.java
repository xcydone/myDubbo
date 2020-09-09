package com.crossyf.dubbo.springtest.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;


@Data
public class PartnerQryDto{

    private int id;

    private String name;

    private String levelName;
}
