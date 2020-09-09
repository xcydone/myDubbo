package com.crossyf.dubbo.springtest.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("conf_table_execution")
public class ConfTableExecutionDto implements Serializable {

    private static final long serialVersionUID = -5353276214475724067L;

    @TableId(value = "table_id", type = IdType.AUTO)
    private Integer tableId;

    @TableField(value = "select_sql")
    private String selectSqlwe;
}
