package com.lopy.common.vo.restaurant;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {
    private Long id;
    private Long restaurantId;
    private Long categoryId;
    private String menuName;
    private Integer status;
    private List<MenuItemVO> menuItemList;
}
