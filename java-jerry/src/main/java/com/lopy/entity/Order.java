package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_order")
public class Order extends Base {
	private String uid;
	private Long restaurantId;
	private Long userId;
	private Integer status;
	private Long paymentIntentId;
	private Double totalCost;
	private Double taxes;
	private Double discounts;
	private String voidRemark;

	// The attributes below are used for DAO mapping only
	@TableField(exist = false)
	private String restaurantName;
}
