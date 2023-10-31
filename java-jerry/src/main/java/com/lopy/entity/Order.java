package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_order")
public class Order extends Base {
	private Long restaurantId;
	private Long userId;
	private Integer status;
	private Long stripeInvoiceId;
	private Double totalCost;
	private Double taxes;
	private Double discounts;
	private String voidRemark;
}
