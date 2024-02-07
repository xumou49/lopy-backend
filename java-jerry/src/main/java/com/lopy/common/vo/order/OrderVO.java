package com.lopy.common.vo.order;

import lombok.Data;

import java.util.Date;

@Data
public class OrderVO {
    private Long id;
    private String restaurantName;
    private Long restaurantId;
    private Integer status;
    private Double taxes;
    private Double discounts;
    private Date completeDate;
}
