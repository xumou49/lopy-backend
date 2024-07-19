package com.lopy.common.dto.restaurant;

import com.lopy.common.pagination.SearchPage;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantListDTO {

    private SearchPage searchPage = new SearchPage();
    private Boolean promotionSearch = false;
    private String cuisine;
    private String name;
    private List<Integer> idList;
    private String action;
}
