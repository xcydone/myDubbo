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
public class ItemRelDto implements Serializable {

    private static final long serialVersionUID = 6319137598484761478L;

    private Integer relId;

    private Integer itemId;

    private String itemName;

    private Integer parentId;

    private List<ItemRelDto> parentList;


}
