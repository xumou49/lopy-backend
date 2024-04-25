package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_payment_intent")
public class PaymentIntent extends Base {

	private String stripeId;
	private Long amount;
	private String status;
	private String nextAction;
	private Long userId;
}
