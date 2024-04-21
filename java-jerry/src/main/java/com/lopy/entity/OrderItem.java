package com.lopy.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("c_order_item")
public class OrderItem extends Base {

	private Long orderId;
	private String itemName;
	private Integer quantity;
	private Double itemPrice;
}
