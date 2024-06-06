package com.lopy.common.dto.order;

import com.lopy.common.pagination.SearchPage;
import lombok.Data;

@Data
public class OrderListDTO {
    private SearchPage searchPage = new SearchPage();
    /**
     * 1: ongoing order, 2: history order
     */
    private Integer type;
    private String startDate;
    private String endDate;
    private Long userId;
}
