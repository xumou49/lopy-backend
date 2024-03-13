package com.lopy.common.vo.restaurant;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantVO {

    private Long id;
    private String name;
    private String address;
    private String labels;
    private Long restaurateurId;
    private String operatingHours;
    private String contactDetails;
    private String imageUrl;
    private String rating = "4.8";
//    private List<MenuCategoryVO> menuCategoryList;
    private MenuCategoryVO menuCategory;
}
