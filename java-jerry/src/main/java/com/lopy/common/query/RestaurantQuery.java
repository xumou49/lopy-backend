package com.lopy.common.query;

import com.lopy.common.dto.restaurant.RestaurantListDTO;
import lombok.Data;
import java.util.List;

@Data
public class RestaurantQuery {

    private String name;
    private String cuisine;
    private List<String> idList;

    public static RestaurantQuery build(RestaurantListDTO restaurantListDTO) {
        RestaurantQuery restaurantQuery = new RestaurantQuery();
        restaurantQuery.setName(restaurantListDTO.getName());
        restaurantQuery.setCuisine(restaurantListDTO.getCuisine());
        restaurantQuery.setIdList(restaurantListDTO.getIdList());
        return restaurantQuery;
    }
}
