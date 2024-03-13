package com.lopy.common.vo.restaurant;

import lombok.Data;

/**
 * @author Yao Liang
 * @created 05/03/2024 - 8:50 pm
 * @projct java-jerry
 */
@Data
public class MenuItemVO {
    private Long id;
    private Long restaurantId;
    private Long menuId;
    private String itemName;
    private String description;
    private Double price;
    private String imageUrl;
}
