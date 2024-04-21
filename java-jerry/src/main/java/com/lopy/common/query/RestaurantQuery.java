package com.lopy.common.query;

import com.lopy.common.dto.restaurant.RestaurantListDTO;
import lombok.Data;

@Data
public class RestaurantQuery {

    private String name;
    private String cuisine;

    public static RestaurantQuery build(RestaurantListDTO restaurantListDTO) {
        RestaurantQuery restaurantQuery = new RestaurantQuery();
        restaurantQuery.setName(restaurantListDTO.getSearchPage().getKeyword());
        restaurantQuery.setCuisine(restaurantListDTO.getCuisine());
        return restaurantQuery;
    }
}
