package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_menu_category")
public class MenuCategory extends Base {
	private Long id;
	private Long restaurantId;
	private String categoryName;
	private Integer status;
}
