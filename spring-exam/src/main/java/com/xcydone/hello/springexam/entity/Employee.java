package com.xcydone.hello.springexam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  员工实体类
 * </p>
 *
 * @author caif
 * @since 2020-10-20
 */
@Data
@Accessors(chain = true)
public class Employee implements Serializable {

    private static final long serialVersionUID = -2671726036565288197L;

    @TableId(value = "employee_id", type = IdType.AUTO)
    private Integer employeeId;

    private String employeeName;

    private String employeeCode;

    private String companyCode;

    private String deptId;

    private String positionId;

    private String status;
}
