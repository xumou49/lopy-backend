package com.lopy.common.dto.order;

import lombok.Data;

@Data
public class OrderItemDTO {
    private String name;
    private Integer quantity;
    private Double price;
}
