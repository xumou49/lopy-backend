package com.lopy.common.query;

import com.lopy.common.dto.order.OrderListDTO;
import lombok.Data;

@Data
public class OrderQuery {

    private String restaurantName;
    private String startDate;
    private String endDate;
    private Long userId;

    public static OrderQuery build(OrderListDTO orderListDTO) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setStartDate(orderListDTO.getStartDate());
        orderQuery.setEndDate(orderListDTO.getEndDate());
        orderQuery.setRestaurantName(orderListDTO.getSearchPage().getKeyword());
        orderQuery.setUserId(orderListDTO.getUserId());
        return orderQuery;
    }
}
