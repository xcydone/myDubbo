package com.crossyf.dubbo.first.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 工单附件表
 * </p>
 *
 * @author zuos
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wf_order_file")
public class OrderFile implements Serializable {

    private static final long serialVersionUID = -7338735213992237187L;

    private String fileId;

    private Integer orderId;

    private Integer handleId;

    private String fileName;

    private String filePath;

    private Integer uploadStaff;

    private LocalDateTime uploadDate;
}
