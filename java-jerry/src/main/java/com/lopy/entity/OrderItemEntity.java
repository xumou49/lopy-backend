package com.lopy.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_order_item")
public class OrderItemEntity extends BaseEntity {

	private Long orderId;
	private Integer quantity;
	private Double itemPrice;

}
