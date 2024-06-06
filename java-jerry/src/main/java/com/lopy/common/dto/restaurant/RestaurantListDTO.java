package com.lopy.common.dto.restaurant;

import com.lopy.common.pagination.SearchPage;
import lombok.Data;

@Data
public class RestaurantListDTO {

    private SearchPage searchPage = new SearchPage();
    private Boolean promotionSearch = false;
    private String cuisine;
    private String name;
}
