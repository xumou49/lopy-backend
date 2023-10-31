package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_menu_category")
public class MenuCategory extends Base {
	private Long restaurantId;
	private String categoryName;

}
