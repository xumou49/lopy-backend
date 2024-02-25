package com.lopy.common.query;

import com.lopy.common.dto.order.OrderItemDTO;
import lombok.Data;

@Data
public class OrderItemQuery {

    private Long orderId;

    public static OrderItemQuery build(OrderItemDTO orderItemDTO) {
        OrderItemQuery orderItemQuery = new OrderItemQuery();
        orderItemQuery.setOrderId(orderItemDTO.getOrderId());
        return orderItemQuery;
    }
}
