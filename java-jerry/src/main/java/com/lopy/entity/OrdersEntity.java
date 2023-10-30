package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_orders")
public class OrdersEntity extends BaseEntity {
	private Long restaurantId;
	private Long userId;
	private Integer status;
	private Long stripeInvoiceId;
	private Double totalCost;
	private Double taxes;
	private Double discounts;
	private Date createdAt;
	private Date updatedAt;
	private String voidRemark;

}
