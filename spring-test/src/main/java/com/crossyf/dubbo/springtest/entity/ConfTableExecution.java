package com.eshore.springbatch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zuos
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConfTableExecution implements Serializable {

    private static final long serialVersionUID = -3683443326700560417L;

    @TableId(value = "table_id", type = IdType.AUTO)
    private Integer tableId;

    private String selectSql;

    private String separation;

    private String writerTableName;

    private String writerColumnNames;

    private String writerFileName;

    private String readerColumnNames;

    private String processorExecutionClass;

    private Integer confStatus;

    private String idColumnName;


}
