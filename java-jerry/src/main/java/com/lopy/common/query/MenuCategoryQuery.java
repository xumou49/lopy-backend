package com.lopy.common.query;

import com.lopy.common.dto.menu.MenuCategoryDTO;
import lombok.Data;

/**
 * @author Yao Liang
 * @created 05/03/2024 - 9:02 pm
 * @projct java-jerry
 */
@Data
public class MenuCategoryQuery {
    private Long restaurantId;
    
    public static MenuCategoryQuery build(MenuCategoryDTO menuCategoryDTO) {
        MenuCategoryQuery menuCategoryQuery = new MenuCategoryQuery();
        menuCategoryQuery.setRestaurantId(menuCategoryDTO.getRestaurantId());
        return menuCategoryQuery;
    }
}
