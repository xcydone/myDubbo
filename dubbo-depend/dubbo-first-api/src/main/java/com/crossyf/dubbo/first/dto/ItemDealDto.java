package com.crossyf.dubbo.first.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemDealDto implements Serializable {
    private static final long serialVersionUID = -719871288675053956L;

    private String orderId;
    private Integer itemOperType;
}
