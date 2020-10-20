package com.xcydone.hello.springexam.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class EmployeeDto implements Serializable {

    private static final long serialVersionUID = 4993960550126097482L;

    private Integer employeeId;

    private String employeeName;

    private String employeeCode;

    private String companyCode;

    private String deptId;

    private String positionId;

    private String status;
}
