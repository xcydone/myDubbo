package com.crossyf.dubbo.springtest.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zuos
 * @since 2020-09-22
 */
@Data
public class ItemTableDto implements Serializable {

    private static final long serialVersionUID = -7547430706494708242L;

    private Integer itemId;

    private String itemName;

    private List<ItemTableDto> parentList;
}
