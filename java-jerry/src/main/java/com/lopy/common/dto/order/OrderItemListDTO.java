package com.lopy.common.dto.order;

import com.lopy.common.pagination.SearchPage;
import lombok.Data;

@Data
public class OrderItemListDTO {
    private Long orderId;
    private SearchPage searchPage = new SearchPage();
}
