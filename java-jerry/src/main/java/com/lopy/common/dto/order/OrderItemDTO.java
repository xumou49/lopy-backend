package com.lopy.common.dto.order;

import lombok.Data;

@Data
public class OrderItemDTO {
    private String itemName;
    private Integer quantity;
    private Double itemPrice;
}
