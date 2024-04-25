package com.lopy.common.dto.payment;

import com.lopy.common.dto.order.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {

    private Long userId;
    private List<OrderItemDTO> itemList = new ArrayList<>();

    public double getTotalCost() {
        return itemList.stream().mapToDouble(o -> o.getPrice() * o.getQuantity()).sum();
    }
}
