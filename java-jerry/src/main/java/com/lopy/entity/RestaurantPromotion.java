package com.lopy.entity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("c_restaurant_promotion")
public class RestaurantPromotion {

	private Long restaurantId;
	private Long promotionId;
	private Date startDate;
	private Date endDate;
	@TableField(fill = FieldFill.INSERT)
	private Date createDate;
}
