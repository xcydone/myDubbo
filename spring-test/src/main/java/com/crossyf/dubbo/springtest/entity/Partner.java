package com.crossyf.dubbo.springtest.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zuos
 * @since 2020-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Partner implements Serializable {

    private static final long serialVersionUID = -6770043148715272125L;

    private String id;

    @TableField(value = "name_20")
    private String name20;

    private String personId;

    private String level;

    @TableField("level_heHis")
    private String levelHehis;
}
