package com.lopy.common.dto.restaurant;

import com.lopy.common.pagination.SearchPage;
import lombok.Data;

@Data
public class RestaurantDTO extends SearchPage {

    private Integer promotionFlag;
}
