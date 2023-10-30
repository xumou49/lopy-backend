package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_menu_item")
public class MenuItemEntity extends BaseEntity {
	private Long restaurantId;
	private String itemName;
	private String description;
	private Double price;

}
