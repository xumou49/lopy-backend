package com.lopy.common.query;

import com.lopy.common.dto.order.OrderItemListDTO;
import lombok.Data;

@Data
public class OrderItemQuery {

    private Long orderId;

    public static OrderItemQuery build(OrderItemListDTO orderItemListDTO) {
        OrderItemQuery orderItemQuery = new OrderItemQuery();
        orderItemQuery.setOrderId(orderItemListDTO.getOrderId());
        return orderItemQuery;
    }
}
