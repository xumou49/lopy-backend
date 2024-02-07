package com.lopy.common.dto.order;

import com.lopy.common.pagination.SearchPage;
import lombok.Data;

@Data
public class OrderDTO {
    private SearchPage searchPage = new SearchPage();
    private String startDate;
    private String endDate;
    private Long userId;
}
