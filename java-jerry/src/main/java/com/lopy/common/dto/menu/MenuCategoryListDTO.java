package com.lopy.common.dto.menu;

import com.lopy.common.pagination.SearchPage;
import lombok.Data;

@Data
public class MenuCategoryListDTO {
    
    private Long restaurantId;
    private SearchPage searchPage = new SearchPage();
}
