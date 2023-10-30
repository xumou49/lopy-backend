package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_seating")
public class SeatingEntity extends BaseEntity {

	private Long restaurantId;
	private Integer tableNumber;
	private Integer seatingCapacity;
	private Integer status;

}
