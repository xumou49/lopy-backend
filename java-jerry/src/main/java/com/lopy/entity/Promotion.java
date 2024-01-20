package com.lopy.entity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_promotion")
public class Promotion extends Base {

	@TableId(type = IdType.AUTO)
	private Long id;
	private String name;
	private Double discount;
	@TableField(fill = FieldFill.UPDATE)
	private Date modifyDate;
}
