package com.lopy.common.vo.order;

import lombok.Data;

@Data
public class OrderItemVO {
    private String itemName;
    private Integer quantity;
    private Double itemPrice;
}
