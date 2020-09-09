package com.crossyf.dubbo.springtest.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zuos
 * @since 2020-09-08
 */
@Data
public class PartnerDto implements Serializable {

    private static final long serialVersionUID = 8948919281390136724L;

    private int id;

    @TableField(value = "name_20")
    private String name20;

    private String personId;

    private String level;

    @TableField("level_heHis")
    private String levelHehis;
}
