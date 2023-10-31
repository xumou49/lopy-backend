package com.lopy.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_restaurateur")
public class Restaurateur extends Base {
	private Long userId;

}
