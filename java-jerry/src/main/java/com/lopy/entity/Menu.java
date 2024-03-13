package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_menu")
public class Menu extends Base {
    @TableId
    private Long id;
    private Long restaurantId;
    private Long categoryId;
    private String menuName;
    /**
     * status [0 -> disable；1 -> enable;]
     */
    private Integer status;

}
