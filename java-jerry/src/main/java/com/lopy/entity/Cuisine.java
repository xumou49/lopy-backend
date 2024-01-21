package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;


@Data
@TableName("c_cuisine")
public class Cuisine {

	@TableId(type = IdType.AUTO)
	private Long id;
	private String name;
	private String imagePath;
	@TableField(fill = FieldFill.UPDATE)
	private Date modifyDate;
}
