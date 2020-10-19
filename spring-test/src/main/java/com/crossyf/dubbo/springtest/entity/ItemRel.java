package com.crossyf.dubbo.springtest.entity;

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
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ItemRel implements Serializable {

    private static final long serialVersionUID = -4447920533150120179L;
    @TableId(value = "rel_id", type = IdType.AUTO)
    private Integer relId;

    private Integer itemId;

    private Integer parentId;


}
