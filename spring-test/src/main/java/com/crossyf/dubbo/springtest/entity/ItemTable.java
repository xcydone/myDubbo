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
public class ItemTable implements Serializable {


    private static final long serialVersionUID = 179132089016703636L;
    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer itemId;

    private String itemName;


}
