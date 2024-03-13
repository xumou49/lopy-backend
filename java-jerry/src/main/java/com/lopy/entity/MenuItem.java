package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_menu_item")
public class MenuItem extends Base {
    private Long id;
    private Long restaurantId;
    private Long menuId;
    private String itemName;
    private String description;
    private Double price;
    private String imagePath;
}
