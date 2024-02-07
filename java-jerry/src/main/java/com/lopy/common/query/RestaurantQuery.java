package com.lopy.common.query;

import com.lopy.common.dto.restaurant.RestaurantDTO;
import lombok.Data;

@Data
public class RestaurantQuery {

    private String name;
    private String cuisine;

    public static RestaurantQuery build(RestaurantDTO restaurantDTO) {
        RestaurantQuery restaurantQuery = new RestaurantQuery();
        restaurantQuery.setName(restaurantDTO.getSearchPage().getKeyword());
        restaurantQuery.setCuisine(restaurantDTO.getCuisine());
        return restaurantQuery;
    }
}
