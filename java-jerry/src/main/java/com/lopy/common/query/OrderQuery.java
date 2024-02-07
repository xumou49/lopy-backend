package com.lopy.common.query;

import com.lopy.common.dto.order.OrderDTO;
import lombok.Data;

@Data
public class OrderQuery {

    private String restaurantName;
    private String startDate;
    private String endDate;
    private Long userId;

    public static OrderQuery build(OrderDTO orderDTO) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setStartDate(orderDTO.getStartDate());
        orderQuery.setEndDate(orderDTO.getEndDate());
        orderQuery.setRestaurantName(orderDTO.getSearchPage().getKeyword());
        orderQuery.setUserId(orderDTO.getUserId());
        return orderQuery;
    }
}
