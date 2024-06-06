package com.lopy.common.dto.order;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {

    private Long cardId;
    private Long restaurantId;
    private Long userId;
    private List<OrderItemDTO> itemList = new ArrayList<>();

    public double getTotalCost() {
        return itemList.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum();
    }
}
