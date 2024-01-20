package com.lopy.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_restaurant")
public class Restaurant extends Base {
	private String name;
	private String address;
	private String labels;
	private Long restaurateurId;
	private String operatingHours;
	private String contactDetails;
}
