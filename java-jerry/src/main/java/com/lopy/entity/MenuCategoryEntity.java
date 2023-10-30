package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_menu_category")
public class MenuCategoryEntity extends BaseEntity {
	private Long restaurantId;
	private String categoryName;

}
